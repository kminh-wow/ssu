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

    else:
        return None, ["(no breakdown available)"]

# ✅ Main loop: input + output
while True:
    line = input(">> Enter PC(hex) and instruction (ex: 0x14 bne a5, zero, L2):\n> ")
    if not line or line.lower() in {"exit", "quit"}:
        break
    try:
        pc_str, *inst_parts = line.strip().split()
        pc = int(pc_str, 16)
        inst = " ".join(inst_parts)
        bin_code, breakdown = parse_instruction(inst, pc)
        hex_code = hex(int(bin_code, 2))[2:].zfill(8)
        print(f"\nBinary   : {bin_code}")
        print(f"Hex      : {hex_code}")
        print("\nBreakdown:")
        for b in breakdown:
            print(b)
        print()
    except Exception as e:
        print(f"Error: {e}\n")
