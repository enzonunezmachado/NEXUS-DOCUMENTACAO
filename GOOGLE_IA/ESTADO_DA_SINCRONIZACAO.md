# Estado da sincronização GOOGLE_IA

**Última sincronização:** 2026-11-23  
**Origem:** `enzonunezmachado/PROJETO-NEXUS-`  
**Branch:** `main`  
**Destino:** `enzonunezmachado/NEXUS-DOCUMENTACAO`  
**Branch de destino:** `main`

## Resultado desta sincronização

A pasta `GOOGLE_IA/docs/` foi atualizada para refletir a documentação técnica versionada no repositório ativo. Foram preservados os caminhos relativos da origem.

Conteúdo espelhado:
- `README.md` do diretório `docs/`;
- 13 documentos numerados (`01` a `13`);
- `HISTORICO_DOCUMENTACAO.md`;
- três documentos em `docs/archive/`.

**Total esperado: 19 arquivos Markdown** (índice + 13 documentos numerados + histórico metodológico + 3 arquivos arquivados).

Nesta rodada (2026-11-23) foram adicionados ao espelho:
- `docs/00_ORIENTACAO_E_AUTORIDADE.md` (orientação obrigatória de leitura e autoridade).
- `docs/13_GOVERNANCA_DOCUMENTAL.md`;
- `docs/HISTORICO_DOCUMENTACAO.md`.

Também foram corrigidos no repositório ativo e sincronizados para o espelho:
- o índice `docs/README.md`, incluindo os novos documentos, corrigindo quebras de linha literais e atualizando o nome do repositório;
- a formatação da seção de evolução documental em `docs/01_DOCUMENTO_MESTRE.md`.

## Separação de responsabilidades confirmada

- `PROJETO-NEXUS-/docs/`: documentação técnica vigente do aplicativo, mantida junto ao repositório ativo.
- `NEXUS-DOCUMENTACAO/GOOGLE_IA/`: espelho dessa documentação técnica para consulta e continuidade.
- `NEXUS-DOCUMENTACAO/CHATGPT/`: memória estratégica, conceitual e histórica das conversas, ideias, propostas e decisões.
- `NEXUS-DOCUMENTACAO/docs/`: acervo legado/histórico da organização documental anterior; não é uma terceira área vigente.
- Artefatos internos do Google AI Studio: memória operacional complementar; não foram acessados nem alterados nesta sincronização.

## Limites desta verificação

- A sincronização foi feita na branch `main`.
- Os dois novos documentos foram copiados do repositório ativo para `GOOGLE_IA/docs/`.
- Os índices foram atualizados para refletir a nova contagem e os novos caminhos.
- Os 18 arquivos Markdown foram comparados por SHA de blob do GitHub, e os 18 hashes coincidiram com os arquivos correspondentes da origem.
- Não foram executados build, testes Android, exportação ou inspeção de ZIP.
- Nenhum código Android foi alterado.


## Atualização de autoridade documental — 2026-11-23

O espelho técnico recebeu as atualizações de governança do repositório ativo:
- novo `GOOGLE_IA/docs/00_ORIENTACAO_E_AUTORIDADE.md`;
- atualização do índice técnico, Documento Mestre, ADR-003, governança e histórico documental.

A memória estratégica recebeu:
- `CHATGPT/00_ORIENTACAO_DE_LEITURA.md`;
- referências obrigatórias nos READMEs;
- alinhamento da visão estratégica sobre profundidade acessível, clareza das conexões e adaptação a telas maiores;
- esclarecimento de que a diretriz local-first/offline-first está vigente e pode ser revista formalmente, mas não é revogada por propostas em análise.

**Limites:** commits documentais realizados nos dois repositórios; nenhum código Android foi alterado. Os artefatos internos do Google AI Studio não foram acessados nem atualizados. A sincronização dos arquivos Markdown do espelho técnico foi realizada a partir dos arquivos do repositório ativo; a comparação de hashes completa deverá ser verificada separadamente.


### Complemento de sincronização — 2026-11-23

Após a primeira atualização de autoridade, a varredura adicional identificou linguagem excessivamente absoluta em:
- `docs/01_DOCUMENTO_MESTRE.md`: título de princípios permanentes;
- `docs/09_GUIA_DESENVOLVIMENTO.md`: regras permanentes e proibição absoluta de reescrita/backend.

Os textos foram ajustados na origem e sincronizados para `GOOGLE_IA/docs/`. O Guia agora deixa explícito que as regras são vigentes enquanto as decisões correspondentes estiverem ativas, e que mudanças exigem justificativa, análise e aprovação formal.
