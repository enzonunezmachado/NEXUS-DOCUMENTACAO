# GOOGLE_IA — Espelho da documentação técnica do NEXUS

Esta pasta preserva a documentação técnica produzida e mantida pelo ambiente Google AI Studio no repositório ativo **Projeto Nexus**.

## Fonte e precedência

1. Código em `enzonunezmachado/PROJETO-NEXUS-`: fonte de verdade da implementação real.
2. Markdown versionado em `docs/` nesse repositório: documentação técnica oficial.
3. `GOOGLE_IA/docs/` aqui: espelho dos arquivos técnicos versionados, para continuidade e consulta.
4. Artefatos internos do AI Studio, especialmente `NEXUS_CONTINUIDADE`: memória operacional complementar, nunca fonte única.

## Estrutura espelhada

A árvore `docs/` reproduz os caminhos da origem:

- [Índice técnico](docs/README.md)
- [01 — Documento Mestre](docs/01_DOCUMENTO_MESTRE.md)
- [02 — Especificação do Produto](docs/02_ESPECIFICACAO_PRODUTO.md)
- [03 — Arquitetura de Engenharia](docs/03_ARQUITETURA_ENGENHARIA.md)
- [04 — Modelo de Dados](docs/04_MODELO_DADOS.md)
- [05 — Fluxos e Comportamentos](docs/05_FLUXOS_COMPORTAMENTOS.md)
- [06 — Design System](docs/06_DESIGN_SYSTEM.md)
- [07 — Estado Atual](docs/07_ESTADO_ATUAL.md)
- [08 — Qualidade e Segurança](docs/08_QUALIDADE_SEGURANCA.md)
- [09 — Guia de Desenvolvimento](docs/09_GUIA_DESENVOLVIMENTO.md)
- [10 — Roadmap](docs/10_ROADMAP.md)
- [11 — ADRs](docs/11_DECISOES_ADRS.md)
- [12 — Rastreabilidade](docs/12_RASTREABILIDADE.md)
- [Arquivo histórico](docs/archive/)

## Regra de sincronização

Em cada sincronização:
1. Consultar novamente a branch `main` do repositório ativo.
2. Inventariar `docs/` e `docs/archive/`.
3. Comparar cada caminho e conteúdo com o espelho.
4. Atualizar arquivos modificados, criar arquivos novos e preservar os históricos.
5. Registrar commit de origem, data, arquivos copiados, divergências e limitações em [Estado da sincronização](ESTADO_DA_SINCRONIZACAO.md).
6. Verificar integridade do conteúdo e links relativos.

Não copiar relatórios resumidos no lugar dos documentos originais. Não declarar build, teste, ZIP ou sincronização de artefatos internos sem evidência.

## Observação de nomenclatura

O índice original da origem ainda contém uma referência textual antiga a `PROJECT_NEXUS`. O nome atual do repositório ativo é `PROJETO-NEXUS-`. O espelho mantém os documentos de origem fiéis; esta observação evita confusão até que a origem seja corrigida pelo mantenedor do projeto.
