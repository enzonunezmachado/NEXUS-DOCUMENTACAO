# ATUALIZAÇÃO — Reformulação ampla da experiência do NEXUS

> **Estado geral: EM ANÁLISE.** Esta pasta reúne a direção conceitual, os requisitos desejados, hipóteses e questões abertas para uma evolução ampla da experiência do NEXUS. Não é especificação técnica normativa, decisão final de arquitetura ou autorização de implementação.

## Objetivo

Reavaliar como o NEXUS é apresentado e vivido pelo usuário: organização das áreas, modelo mental, linguagem visual, componentes, fichas, relações, interações, navegação e adaptação a diferentes telas.

A reformulação é ampla na experiência, mas não implica reescrever o aplicativo ou alterar automaticamente seu núcleo técnico e modelo de dados. Qualquer mudança em domínio, persistência, regras ou arquitetura de software exige análise e aprovação próprias.

## Como ler

**Comece pelo [Plano Mestre de Evolução](PLANO_MESTRE_DE_EVOLUCAO.md)**: ele apresenta o estado das rodadas, prioridades, dependências, critérios de conclusão e próxima ação. Esta pasta é o centro de coordenação estratégica da evolução.

1. [Plano Mestre de Evolução](PLANO_MESTRE_DE_EVOLUCAO.md)
2. [00 — Visão, escopo e governança](00_VISAO_ESCOPO_E_GOVERNANCA.md)
3. [01 — Arquitetura da informação](01_ARQUITETURA_DA_INFORMACAO.md)
4. [02 — Linguagem visual, cores e símbolos](02_LINGUAGEM_VISUAL_CORES_E_SIMBOLOS.md)
5. [03 — Componentes, cards e fichas](03_COMPONENTES_CARDS_E_FICHAS.md)
6. [04 — Interações, relações e navegação](04_INTERACOES_RELACOES_E_NAVEGACAO.md)
7. [05 — Plano de evolução e questões abertas](05_PLANO_DE_EVOLUCAO_E_QUESTOES_ABERTAS.md)
8. [06 — Evolução conceitual e arquitetural](06_EVOLUCAO_CONCEITUAL_E_ARQUITETURAL.md)
9. [07 — Requisitos transversais e casos de uso](07_REQUISITOS_TRANSVERSAIS_E_CASOS_DE_USO.md)

## Como interpretar esta documentação

- Os documentos 00–07 descrevem a direção atual de investigação por assunto.
- O documento 06 registra a evolução do pensamento: o que foi consolidado, reformulado, reduzido, substituído ou mantido em aberto em relação às versões anteriores.
- A proposta Claude de 24/11/2026 continua preservada na pasta [PROPOSTAS](../PROPOSTAS/2026-11-24_ARQUITETURA_INFORMACAO/README.md), como referência e alternativa, não como arquitetura escolhida. Ela também foi separada em [sete partes temáticas fiéis](../PROPOSTAS/2026-11-24_ARQUITETURA_INFORMACAO/CLAUDE_DESMEMBRADO/README.md), mantendo a fonte integral intacta.
- Quando uma questão não estiver resolvida, ela deve permanecer explicitamente aberta. Não preencher lacunas com suposições.

## Fontes de contexto

- [Rodada de arquitetura da informação de 24/11/2026](../PROPOSTAS/2026-11-24_ARQUITETURA_INFORMACAO/README.md).
- [Visão e princípios do NEXUS](../01_VISAO_E_PRINCIPIOS.md).
- [Decisões de UX e produto já registradas](../08_DECISOES_DE_UX_E_PRODUTO.md).
- [Espelho técnico](../../GOOGLE_IA/docs/README.md), subordinado ao código e à documentação técnica original do repositório ativo.

## Governança

- Esta pasta preserva intenção e evolução conceitual; não substitui documentação técnica.
- Propostas e protótipos não revogam decisões aprovadas apenas por serem mais recentes.
- A proposta Claude é uma hipótese estrutural, não uma decisão.
- Antes de mudar regras, dados, navegação ou contratos, verificar o código e os documentos técnicos.
- Uma direção aprovada estrategicamente ainda precisa de especificação, planejamento, autorização e testes.
- Não declarar algo implementado ou validado sem evidência no aplicativo real.

## Estado atual

A direção conceitual foi refinada e agora é coordenada pelo Plano Mestre. A iniciativa combina:
1. Uma experiência visual mais expressiva e bem composta, sem perder a identidade sóbria do NEXUS.
2. Uma arquitetura da informação ainda em investigação, sem adoção automática dos cinco destinos propostos pelo Claude.
3. Uma linguagem de componentes proporcional à função e à densidade de cada informação.
4. A separação clara entre tipo de registro, estado, vínculo e ação.
5. Uma evolução incremental baseada em auditoria do aplicativo real e validação humana.

**Nada nesta pasta, por si só, autoriza implementação.**


## Integração ChatGPT ↔ NEXUS

A integração atual de entrada permanece funcional, mas sua finalização bidirecional foi posicionada como **Rodada 6**, após completar o domínio funcional (Rodada 4) e estabilizar a experiência (Rodada 5). O guia operacional `docs/16_GUIA_OPERACIONAL_CHATGPT_NEXUS.md` será gerado e validado pelo Google AI Studio a partir do código real quando os modelos estiverem estáveis. Esta pasta estratégica não substitui esse guia técnico.
