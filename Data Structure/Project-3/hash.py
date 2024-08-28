import os.path
import random
import time
import copy
import matplotlib.pyplot as plt
import math
import numpy as np
import zipfile
import json
import shutil

tamanho_tabela = 50_000

class HashFechado:
    nome = "Hash Com Endereço Fechado"

    def __init__(self, tamanho_tabela=tamanho_tabela):
        self.tamanho_tabela = tamanho_tabela
        self.tabela = [None] * tamanho_tabela
        self.fator_carga = 0

    def hash_function(self, chave):
        return hash(chave) % self.tamanho_tabela

    def inserir(self, chave):
        indice = self.hash_function(chave)

        if self.tabela[indice] is None:
            self.tabela[indice] = (chave, chave)
        else:
            i = 1
            while self.tabela[indice] is not None:
                indice = (indice + i) % self.tamanho_tabela
                i += 1
            self.tabela[indice] = (chave, chave)

        self.fator_carga += 1

    def buscar(self, chave):
        indice = self.hash_function(chave)

        if self.tabela[indice] is not None and self.tabela[indice][0] == chave:
            return self.tabela[indice][1]
        else:
            i = 1
            while self.tabela[indice] is not None and self.tabela[indice][0] != chave:
                indice = (indice + i) % self.tamanho_tabela
                i += 1

            if self.tabela[indice] is not None:
                return self.tabela[indice][1]
            else:
                return None

    def clear(self):
        self.tabela = [None] * self.tamanho_tabela
        self.fator_carga = 0

class HashAberto:
    nome = "Hash com Endereço Aberto (Hashing Duplo)"

    def __init__(self, tamanho_tabela=tamanho_tabela):
        self.tamanho_tabela = tamanho_tabela
        self.tabela = [None] * tamanho_tabela
        self.fator_carga = 0

    def hash_function(self, chave):
        return hash(chave) % self.tamanho_tabela

    def hash_function_2(self, chave):
        return 7 - (hash(chave) % 7)

    def inserir(self, chave):
        indice = self.hash_function(chave)

        if self.tabela[indice] is None:
            self.tabela[indice] = (chave, chave)
        else:
            i = 1
            while self.tabela[indice] is not None:
                indice = (indice + i * self.hash_function_2(chave)) % self.tamanho_tabela
                i += 1
            self.tabela[indice] = (chave, chave)

        self.fator_carga += 1

    def buscar(self, chave):
        indice = self.hash_function(chave)

        if self.tabela[indice] is not None and self.tabela[indice][0] == chave:
            return self.tabela[indice][1]
        else:
            i = 1
            while self.tabela[indice] is not None and self.tabela[indice][0] != chave:
                indice = (indice + i * self.hash_function_2(chave)) % self.tamanho_tabela
                i += 1

            if self.tabela[indice] is not None:
                return self.tabela[indice][1]
            else:
                return None

    def clear(self):
        self.tabela = [None] * self.tamanho_tabela
        self.fator_carga = 0



class Vector:

    @staticmethod
    def gerarVetorAleatorioSemRepeticao(size, intervalo):
        limiteInferior, limiteSuperior = intervalo

        listSet = set()

        while len(listSet) < size:
              numeroAleatorio = random.randint(limiteInferior, limiteSuperior)
              listSet.add(numeroAleatorio)

        return list(listSet)

    @staticmethod
    def gerarVetorAleatorioComRepeticao(size, intervalo):
        limiteInferior, limiteSuperior = intervalo

        return [random.randint(limiteInferior, limiteSuperior) for _ in range(size)]

class HashBenchmark:

    INTERVALO_VETORES = (0, 1_000_000)
    HASHS = [
        HashAberto(),
        HashFechado()
    ]

    TAMANHOS_VETORES_INSERCAO = [3_000, 30_000]
    TAMANHOS_VETORES_BUSCA = [50_000, 500_000]

    TIPOS_VETORES_INSERCAO = [
      {
          "nome": "Ordem Aleatória (Sem Repetições)",
          "vetor": Vector.gerarVetorAleatorioSemRepeticao
      }
    ]
    TIPOS_VETORES_BUSCA = [
       {
          "nome": "Ordem Aleatória (Com Repetições)",
          "vetor": Vector.gerarVetorAleatorioComRepeticao
      }
    ]

    VETORES_INSERCAO = None
    VETORES_BUSCA = None

    DIR_HASH = "graficosHash"
    INSERCAO = "Inserção"
    BUSCA = "Busca"

    def setarVetores(self):
        vetoresInsercao = {}
        vetoresBusca = {}

        for tipo in self.TIPOS_VETORES_INSERCAO:
            vetoresInsercao[tipo["nome"]] = [tipo["vetor"](tam, self.INTERVALO_VETORES) for tam in self.TAMANHOS_VETORES_INSERCAO]

        for tipo in self.TIPOS_VETORES_BUSCA:
            vetoresBusca[tipo["nome"]] = [tipo["vetor"](tam, self.INTERVALO_VETORES) for tam in self.TAMANHOS_VETORES_BUSCA]


        self.VETORES_INSERCAO = vetoresInsercao
        self.VETORES_BUSCA = vetoresBusca


    def benchmarkInsercao(self, hash, vetor):
        inicio = time.time()

        for data in vetor:
          hash.inserir(data)

        final = time.time()
        duracao = final - inicio

        return {
            "tempo": round(duracao, 5) ,
            "tamanho": len(vetor)
         }

    def benchmarkBusca(self, hash, vetor):
        inicio = time.time()

        for data in vetor:
          hash.buscar(data)

        final = time.time()
        duracao = final - inicio

        return {
            "tempo": round(duracao, 5),
            "tamanho": len(vetor)
         }

    def obterDados(self):
        benchmarks = {
            self.INSERCAO: {},
            self.BUSCA: {},
        }

        for hash in self.HASHS:

            for nomeVetorInsercao, matrizVetorInsercao in self.VETORES_INSERCAO.items():

                if nomeVetorInsercao not in benchmarks[self.INSERCAO]:
                    benchmarks[self.INSERCAO][nomeVetorInsercao] = {}

                for vetorInsercao in matrizVetorInsercao:
                    lenVetor = len(vetorInsercao)
                    benchmarkInsercao = self.benchmarkInsercao(hash, vetorInsercao)

                    if hash.nome not in benchmarks[self.INSERCAO][nomeVetorInsercao]:
                       benchmarks[self.INSERCAO][nomeVetorInsercao][hash.nome] = []

                    benchmarks[self.INSERCAO][nomeVetorInsercao][hash.nome].append(benchmarkInsercao)

                    for nomeVetorBusca, matrizVetorBusca in self.VETORES_BUSCA.items():

                        if nomeVetorBusca not in benchmarks[self.BUSCA]:
                           benchmarks[self.BUSCA][nomeVetorBusca] = {}

                        for vetorBusca in matrizVetorBusca:
                            benchmarkBusca = self.benchmarkBusca(hash, vetorBusca)

                            if hash.nome not in benchmarks[self.BUSCA][nomeVetorBusca]:
                               benchmarks[self.BUSCA][nomeVetorBusca][hash.nome] = {}

                            if lenVetor not in benchmarks[self.BUSCA][nomeVetorBusca][hash.nome]:
                               benchmarks[self.BUSCA][nomeVetorBusca][hash.nome][lenVetor] = []

                            benchmarks[self.BUSCA][nomeVetorBusca][hash.nome][lenVetor].append(benchmarkBusca)

                    hash.clear()

        return benchmarks


    def ordenarVetores(self, vetor):
        vetor.append(0)
        vetor.sort()

    def salvarGraficoInsercao(self, dados):

        for tipoVetor, dadosTipoVetor in dados[self.INSERCAO].items():

            plt.figure(figsize=(10, 5))

            for nomeHash, benchmarks in dadosTipoVetor.items():
                tamanhos = [benchmark["tamanho"] for benchmark in benchmarks]
                tempos_execucao = [benchmark["tempo"] for benchmark in benchmarks]

                self.ordenarVetores(tamanhos)
                self.ordenarVetores(tempos_execucao)

                plt.plot(tamanhos, tempos_execucao, marker='o', label=nomeHash)

            plt.title(f'Tempo de Execução Com {tipoVetor} - {self.INSERCAO}')
            plt.xlabel('Tamanho do Vetor')
            plt.ylabel('Tempo (Segundos)')
            plt.legend()
            plt.show()

            if not os.path.exists(self.DIR_HASH):
                os.mkdir(self.DIR_HASH)

            path = os.path.join(self.DIR_HASH, f"Gráfico-{tipoVetor}-{self.INSERCAO}-{nomeHash}.png")
            plt.savefig(path)

    def salvarGraficoBusca(self, dados):

        for tipoVetor, dadosTipoVetor in dados[self.BUSCA].items():

            for nomeHash, dadosVetorInsercao in dadosTipoVetor.items():
                plt.figure(figsize=(10, 5))

                for tamanhoVetorInsercao, benchmarks in dadosVetorInsercao.items():
                    tamanhos = [benchmark["tamanho"] for benchmark in benchmarks]
                    tempos_execucao = [benchmark["tempo"] for benchmark in benchmarks]

                    self.ordenarVetores(tamanhos)
                    self.ordenarVetores(tempos_execucao)

                    plt.plot(tamanhos, tempos_execucao, marker='o', label=tamanhoVetorInsercao)

                plt.title(f'Tempo de Execução Com {tipoVetor} - {nomeHash} - {self.BUSCA}')
                plt.xlabel('Tamanho do Vetor')
                plt.ylabel('Tempo (Segundos)')
                plt.legend()

                if not os.path.exists(self.DIR_HASH):
                    os.mkdir(self.DIR_HASH)

                path = os.path.join(self.DIR_HASH, f"Gráfico-{tipoVetor}-{self.BUSCA}-{tamanhoVetorInsercao}-{nomeHash}.png")
                plt.savefig(path)


    def salvarGraficos(self, dados):
        self.salvarGraficoInsercao(dados)
        self.salvarGraficoBusca(dados)

    def zip_folder(self, folder_path):
        with zipfile.ZipFile(f'{folder_path}.zip', 'w', zipfile.ZIP_DEFLATED) as zipf:
          for root, dirs, files in os.walk(folder_path):
              for file in files:
                  file_path = os.path.join(root, file)
                  arcname = os.path.relpath(file_path, folder_path)
                  zipf.write(file_path, arcname)

    def salvarDados(self, dados):
        path_json = os.path.join('dados.json')

        with open(path_json, 'w') as json_file:
             json.dump(dados, json_file, indent=2)

    def ziparPastas(self):
        self.zip_folder(self.DIR_HASH)
        shutil.rmtree(self.DIR_HASH)

    def run(self):
        self.setarVetores()

        dados = self.obterDados()

        self.salvarGraficos(dados)
        self.salvarDados(dados)
        self.ziparPastas()

hashBenchmark = HashBenchmark()
hashBenchmark.run()