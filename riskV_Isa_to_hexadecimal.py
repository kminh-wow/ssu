reg_map = {
    "zero": 0, "ra": 1, "sp": 2,
    "a0": 10, "a1": 11, "a2": 12, "a3": 13, "a4": 14, "a5": 15
}

label_to_pc = {
    "L1": 0x24, "L2": 0x34, "L3": 0x50, "HW2": 0x00
}

def to_bin(val, bits):
    if val < 0:
        val = (1 << bits) + val
    return format(val, f'0{bits}b')

def resolve_offset(current_pc, label):
    return label_to_pc[label] - current_pc

def parse_instruction(inst, current_pc):
    parts = inst.replace(",", "").replace("(", " ").replace(")", "").split()
    mnemonic = parts[0]

    def fmt(label, bits):
        return f"{label:<10}: {bits} ({int(bits, 2)})"

    if mnemonic == "lw":
        rd, imm, rs1 = parts[1], int(parts[2]), parts[3]
        imm_bin = to_bin(imm, 12)
        rs1_bin = to_bin(reg_map[rs1], 5)
        funct3 = "010"
        rd_bin = to_bin(reg_map[rd], 5)
        opcode = "0000011"
        full = imm_bin + rs1_bin + funct3 + rd_bin + opcode
        breakdown = [
            fmt("imm[11:0]", imm_bin),
            fmt("rs1", rs1_bin),
            fmt("funct3", funct3),
            fmt("rd", rd_bin),
            fmt("opcode", opcode)
        ]
        return full, breakdown

    elif mnemonic == "sw":
        rs2, imm, rs1 = parts[1], int(parts[2]), parts[3]
        imm_bin = to_bin(imm, 12)
        imm_hi, imm_lo = imm_bin[:7], imm_bin[7:]
        rs2_bin = to_bin(reg_map[rs2], 5)
        rs1_bin = to_bin(reg_map[rs1], 5)
        funct3 = "010"
        opcode = "0100011"
        full = imm_hi + rs2_bin + rs1_bin + funct3 + imm_lo + opcode
        breakdown = [
            fmt("imm[11:5]", imm_hi),
            fmt("rs2", rs2_bin),
            fmt("rs1", rs1_bin),
            fmt("funct3", funct3),
            fmt("imm[4:0]", imm_lo),
            fmt("opcode", opcode)
        ]
        return full, breakdown

    elif mnemonic == "addi":
        rd, rs1, imm = parts[1], parts[2], int(parts[3])
        imm_bin = to_bin(imm, 12)
        rs1_bin = to_bin(reg_map[rs1], 5)
        funct3 = "000"
        rd_bin = to_bin(reg_map[rd], 5)
        opcode = "0010011"
        full = imm_bin + rs1_bin + funct3 + rd_bin + opcode
        breakdown = [
            fmt("imm[11:0]", imm_bin),
            fmt("rs1", rs1_bin),
            fmt("funct3", funct3),
            fmt("rd", rd_bin),
            fmt("opcode", opcode)
        ]
        return full, breakdown

    elif mnemonic == "sub":
        rd, rs1, rs2 = parts[1], parts[2], parts[3]
        funct7 = "0100000"
        rs2_bin = to_bin(reg_map[rs2], 5)
        rs1_bin = to_bin(reg_map[rs1], 5)
        funct3 = "000"
        rd_bin = to_bin(reg_map[rd], 5)
        opcode = "0110011"
        full = funct7 + rs2_bin + rs1_bin + funct3 + rd_bin + opcode
        breakdown = [
            fmt("funct7", funct7),
            fmt("rs2", rs2_bin),
            fmt("rs1", rs1_bin),
            fmt("funct3", funct3),
            fmt("rd", rd_bin),
            fmt("opcode", opcode)
        ]
        return full, breakdown
    elif mnemonic in {"bne", "bge"}:
        rs1_name, rs2_name, label = parts[1], parts[2], parts[3]
        real_offset = resolve_offset(current_pc, label)
        
        offset = real_offset  
        imm = to_bin(offset, 13)
        
        imm12   = imm[0]
        imm11   = imm[1]
        imm10_5 = imm[2:8]
        imm4_1  = imm[8:12]
        rs1_bin = to_bin(reg_map[rs1_name], 5)
        rs2_bin = to_bin(reg_map[rs2_name], 5)
        funct3  = "001" if mnemonic == "bne" else "101"
        opcode  = "1100011"
        
        binary = (
            imm12 +          # bit 31
            imm10_5 +        # bits 30-25
            rs2_bin +        # bits 24-20
            rs1_bin +        # bits 19-15
            funct3 +         # bits 14-12
            imm4_1 +         # bits 11-8
            imm11 +          # bit 7
            opcode           # bits 6-0
        )

        breakdown = [
            fmt("imm[12]", imm12),
            fmt("imm[10:5]", imm10_5),
            fmt("rs2", rs2_bin),
            fmt("rs1", rs1_bin),
            fmt("funct3", funct3),
            fmt("imm[4:1]", imm4_1),
            fmt("imm[11]", imm11),
            fmt("opcode", opcode)
        ]

        return binary, breakdown

    elif mnemonic == "jal":
        rd, label = parts[1], parts[2]
        offset = resolve_offset(current_pc, label)
        offset >>= 1
        imm_bin = to_bin(offset, 21)
        imm20 = imm_bin[0]
        imm10_1 = imm_bin[11:21]
        imm11 = imm_bin[10]
        imm19_12 = imm_bin[1:9]
        rd_bin = to_bin(reg_map[rd], 5)
        opcode = "1101111"
        full = imm20 + imm19_12 + imm11 + imm10_1 + rd_bin + opcode
        breakdown = [
            fmt("imm[20]", imm20),
            fmt("imm[19:12]", imm19_12),
            fmt("imm[11]", imm11),
            fmt("imm[10:1]", imm10_1),
            fmt("rd", rd_bin),
            fmt("opcode", opcode)
        ]
        return full, breakdown

    elif mnemonic == "jalr":
        rd, imm, rs1 = parts[1], int(parts[2]), parts[3]
        imm_bin = to_bin(imm, 12)
        rs1_bin = to_bin(reg_map[rs1], 5)
        funct3 = "000"
        rd_bin = to_bin(reg_map[rd], 5)
        opcode = "1100111"
        full = imm_bin + rs1_bin + funct3 + rd_bin + opcode
        breakdown = [
            fmt("imm[11:0]", imm_bin),
            fmt("rs1", rs1_bin),
            fmt("funct3", funct3),
            fmt("rd", rd_bin),
            fmt("opcode", opcode)
        ]
        return full, breakdown

    else:
        return None, ["(no breakdown available)"]


while True:
    print("debug test")
    line = input(">> Enter PC(hex) and instruction (ex: 0x14 bne a5, zero, L2):\n> ")
    if not line or line.lower() in {"exit", "quit"}:
        break
    try:
        pc_str, *inst_parts = line.strip().split()
        pc = int(pc_str, 16)
        inst = " ".join(inst_parts)
        bin_code, breakdown = parse_instruction(inst, pc)
        if bin_code is None:
            print("Unsupported instruction.")
            continue
        hex_code = hex(int(bin_code, 2))[2:].zfill(8)
        print(f"\nBinary   : {bin_code}")
        print(f"Hex      : {hex_code}")
        print("\nBreakdown:")
        for b in breakdown:
            print(b)
        print()
    except Exception as e:
        print(f"Error: {e}\n")
