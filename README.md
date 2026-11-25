# NEXUS — CENTRAL DE DOCUMENTAÇÃO

> **Leitura obrigatória:** consulte [GOOGLE_IA/docs/00_ORIENTACAO_E_AUTORIDADE.md](GOOGLE_IA/docs/00_ORIENTACAO_E_AUTORIDADE.md) para autoridade técnica e [CHATGPT/00_ORIENTACAO_DE_LEITURA.md](CHATGPT/00_ORIENTACAO_DE_LEITURA.md) para interpretar a memória estratégica. Propostas e brainstorms não revogam decisões vigentes.

> **Repositório:** [enzonunezmachado/NEXUS-DOCUMENTACAO](https://github.com/enzonunezmachado/NEXUS-DOCUMENTACAO)
> **Finalidade:** preservar a memória técnica, estratégica e histórica do projeto NEXUS.

Este repositório é a memória duradoura do NEXUS. Ele separa duas fontes complementares para que uma nova pessoa ou IA consiga compreender tanto a engenharia do aplicativo quanto a evolução das ideias e decisões que orientam o produto.

## Comece por aqui

1. [GOOGLE_IA/](GOOGLE_IA/README.md) — documentação técnica produzida pelo ambiente de desenvolvimento, sincronizada a partir do repositório ativo.
2. [CHATGPT/](CHATGPT/README.md) — memória estratégica das conversas: visão, princípios, ideias, brainstorms, decisões, propostas, histórico e pendências.

## Duas áreas independentes

### GOOGLE_IA — Engenharia e realidade técnica

Espelho da documentação técnica mantida no repositório ativo Projeto Nexus. Registra arquitetura, especificação, dados, fluxos, Design System, estado real, qualidade, guia de desenvolvimento, roadmap, ADRs e rastreabilidade.

### CHATGPT — Memória estratégica e evolução do pensamento

Preserva por que o NEXUS está sendo construído, quais caminhos foram considerados, o que foi discutido, quais decisões foram aprovadas, quais propostas foram encaminhadas e quais questões continuam abertas.

**Regra essencial:** uma ideia não é uma funcionalidade; uma proposta não é uma decisão; uma decisão aprovada não é uma implementação. A implementação só é confirmada pela fonte técnica.

## Hierarquia de confiança

1. Código do repositório ativo: fonte de verdade da implementação real.
2. Markdown versionado em `docs/` no repositório ativo: documentação técnica oficial.
3. `GOOGLE_IA/`: espelho técnico e histórico no repositório documental.
4. `CHATGPT/`: intenção, raciocínio, decisões e memória estratégica.
5. Artefatos internos do Google AI Studio: memória operacional complementar, nunca fonte única de informação essencial.

Em caso de divergência, registrar o conflito e verificar a fonte correspondente. Não inventar uma resolução.

## Repositórios

- Este repositório documental: `enzonunezmachado/NEXUS-DOCUMENTACAO`.
- Repositório ativo do aplicativo: `enzonunezmachado/PROJETO-NEXUS-`.

Não modificar o repositório ativo ao realizar manutenção documental neste repositório. A sincronização de GOOGLE_IA é uma cópia controlada, não uma autorização para alterar o código.

## Iniciativas de arquitetura e experiência

- [Rodada de arquitetura da informação — 24/11/2026](CHATGPT/PROPOSTAS/2026-11-24_ARQUITETURA_INFORMACAO/README.md): proposta Claude, alternativas, comparação crítica e prompt-base. Estado EM ANÁLISE.
- [ATUALIZAÇÃO — Reformulação ampla da experiência](CHATGPT/ATUALIZACAO/README.md): iniciativa que consolida propostas de reconstrução da apresentação das telas, identidade visual, componentes, fichas, relações, interações e plano de evolução. Estado EM ANÁLISE; não autoriza implementação.

## Conteúdo legado

O registro histórico [NEXUS_ARCHITECTURE_AND_PROGRESS.md](ARQUIVO/LEGADO_RAIZ/2026-11-20_NEXUS_ARCHITECTURE_AND_PROGRESS.md) foi retirado da raiz e preservado em `ARQUIVO/LEGADO_RAIZ/`; descreve uma fase anterior e não representa a arquitetura atual sem confirmação técnica.

A pasta [docs/](docs/README.md) preserva documentos e registros da organização documental anterior, incluindo o Documento Mestre histórico, decisões de UX, especificações antigas e logs de revisão. Ela é fonte histórica de recuperação, não uma terceira área de documentação vigente.

## Processo de manutenção

- Registrar novas ideias em CHATGPT com contexto e estado.
- Atualizar decisões estratégicas somente após confirmação explícita.
- Sincronizar GOOGLE_IA após a documentação técnica atualizada estar versionada no repositório ativo.
- Preservar documentos históricos e registrar o que os substituiu.
- Não declarar testes, builds, ZIPs ou implementações sem evidência.
- Manter este README como porta de entrada e revisar os índices após mudanças estruturais.

## Estado da primeira organização

- Estrutura CHATGPT criada e preenchida com memória estratégica recuperável, incluindo decisões de UX e marcos históricos.
- GOOGLE_IA sincronizada com a branch `main` do repositório ativo: 13 documentos técnicos numerados, 1 histórico metodológico, 3 documentos arquivados e 1 índice (18 arquivos no total).
- Os 18 arquivos espelhados foram comparados com a origem e confirmados idênticos byte a byte.
- Os documentos legados em `docs/` foram preservados.
- A recuperação estratégica é parcial: não representa transcrição integral de todas as conversas.

## Arquivo de artefatos operacionais

A pasta [ARQUIVO/ARTEFATOS_DE_TRANSFERENCIA/](ARQUIVO/ARTEFATOS_DE_TRANSFERENCIA/2026-11-22_LOTE_VISUAL_ADAPTATIVO/README.md) preserva arquivos temporários de passagem de contexto entre ferramentas que foram retirados da raiz do repositório ativo do aplicativo. São registros históricos de trabalho, não documentação técnica vigente nem fonte de verdade da implementação.
