# Índice da documentação do NEXUS

Esta pasta contém especificações, decisões, arquitetura, integração, roadmap e registros históricos. A fonte central e continuamente atualizada é o [Documento Mestre](NEXUS_MASTER_CONTEXT.md).

## Documentos normativos / de referência

- [NEXUS_MASTER_CONTEXT.md](NEXUS_MASTER_CONTEXT.md) — contexto, estado reportado, decisões, backlog e continuidade. **Fonte central atual.**
- [NEXUS_PRODUCT_SPEC.md](NEXUS_PRODUCT_SPEC.md) — comportamento desejado dos módulos e regras de produto.
- [lista.md](lista.md) — decisões detalhadas do usuário sobre experiência mobile, Agenda, tarefas, financeiro, Dashboard e critérios de UX.
- [NEXUS_DESIGN_SYSTEM.md](NEXUS_DESIGN_SYSTEM.md) — identidade visual, tokens, componentes, gráficos e padrões de interface.
- [NEXUS_INTEGRATION_MAP.md](NEXUS_INTEGRATION_MAP.md) — integrações, fluxos e auditoria de inconsistências entre módulos.

## Roadmap e registros históricos

- [NEXUS_IMPLEMENTATION_ROADMAP.md](NEXUS_IMPLEMENTATION_ROADMAP.md) — roadmap técnico anterior; sequência superada pelo Documento Mestre.
- [NEXUS_CURRENT_STATE.md](NEXUS_CURRENT_STATE.md) — snapshot antigo da baseline 5.2.0 / Room 5 / Backup 5; não representa o estado mais recente.
- [NEXUS_UPDATE_01_CONTROL_CENTER.md](NEXUS_UPDATE_01_CONTROL_CENTER.md) — prompt operacional histórico, associado a branch e commit antigos.
- [reviewchat.md](reviewchat.md) — log bruto de uma sessão anterior; não é especificação nem fonte de verdade do código.
- [../NEXUS_ARCHITECTURE_AND_PROGRESS.md](../NEXUS_ARCHITECTURE_AND_PROGRESS.md) — documento de arquitetura de fases antigas; ver também versões arquivadas em `archive/`.

## Regra de precedência

1. Documento Mestre para estado reportado, backlog e continuidade.
2. `lista.md` para decisões explícitas de UX do usuário.
3. Product Spec e Design System para comportamento desejado e padrões visuais.
4. Integration Map e Roadmap como mapas técnicos/históricos, sujeitos à verificação do código.
5. Current State, Architecture and Progress, Update 01 e reviewchat como registros históricos. Nunca inferir implementação atual apenas deles.

O código atual deve ser conferido no workspace ativo do Google AI Studio e no repositório `PROJETO-NEXUS-`, não neste repositório documental.


## Arquivo de versões antigas

- [Arquitetura — branch integracao-google](archive/NEXUS_ARCHITECTURE_AND_PROGRESS__integracao-google.md)
- [Arquitetura — branch diagnostico-feature-base](archive/NEXUS_ARCHITECTURE_AND_PROGRESS__diagnostico-feature-base.md)

As cópias preservam versões distintas do documento de arquitetura encontradas em branches históricas. São referências de época, não baseline atual.
