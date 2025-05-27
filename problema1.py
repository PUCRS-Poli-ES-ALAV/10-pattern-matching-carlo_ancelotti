import random
import string

def primeiraOcorrencia(string1, string2):
    iteracoes_externas = 0
    iteracoes_internas = 0
    comparacoes = 0

    for i in range(len(string1)):
        iteracoes_externas += 1
        count = 0
        comparacoes += 1
        if string1[i] == string2[0]:
            for j in range(len(string2)):
                iteracoes_internas += 1
                comparacoes += 1
                if i + j >= len(string1):
                    break
                comparacoes += 1
                if string1[i + j] == string2[j]:
                    count += 1
                else:
                    break
            if count == len(string2):
                return i, iteracoes_externas, iteracoes_internas, comparacoes
    return -1, iteracoes_externas, iteracoes_internas, comparacoes

def main():
    string1 = "ABCDCBDCBDACBDABDCBADF"
    string2 = "ADF"
    resultado, it_ext, it_int, comp = primeiraOcorrencia(string1, string2)
    print(f"resultado = {resultado}")
    print(f"iterações externas = {it_ext}")
    print(f"iterações internas = {it_int}")
    print(f"comparações = {comp}")


    string1_grande = ''.join(random.choices(string.ascii_uppercase, k=500_000))
    string2_grande = ''.join(random.choices(string.ascii_uppercase, k=10))  

    print("\nTeste com strings grandes:")
    resultado, it_ext, it_int, comp = primeiraOcorrencia(string1_grande, string2_grande)
    print(f"resultado = {resultado}")
    print(f"iterações externas = {it_ext}")
    print(f"iterações internas = {it_int}")
    print(f"comparações = {comp}")

if __name__ == '__main__': 
    main()
