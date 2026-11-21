# Observações posteriores ao teste — Três vidas simuladas

Esta pasta reúne o que foi observado após a exploração do perfil Gabriel e as solicitações de evolução que surgiram dessa experiência.

Ela não substitui:
- o relatório técnico de geração e validação dos backups;
- o relatório de testes no aplicativo;
- o catálogo estratégico central em `CHATGPT/03_IDEIAS_E_BRAINSTORMS.md`;
- decisões estratégicas ou documentação técnica vigente.

## Navegação

1. [Problemas e correções solicitadas](01_PROBLEMAS_E_CORRECOES.md) — comportamento funcional indesejado e ajustes visuais explicitamente pedidos.
2. [Ideias de evolução](02_IDEIAS_DE_EVOLUCAO.md) — melhorias de produto e experiência ainda sujeitas a análise.
3. [Visão de longo prazo](03_VISAO_DE_LONGO_PRAZO.md) — possibilidades futuras sem prioridade de implementação definida.
4. [Resultado da exploração do Gabriel](04_RESULTADO_EXPLORACAO_GABRIEL.md) — experiência positiva observada e pontos a preservar.

## Como interpretar os estados

- **OBSERVADO:** comportamento visto durante o uso; não implica causa técnica conhecida.
- **SOLICITADO:** o usuário pediu correção ou mudança; ainda não significa implementado.
- **EM ANÁLISE:** precisa de investigação técnica ou de produto.
- **PROPOSTO:** existe uma solução sugerida, ainda não aprovada.
- **APROVADO:** decisão explícita do usuário para seguir.
- **EM IMPLEMENTAÇÃO:** execução autorizada em andamento.
- **IMPLEMENTADO:** alteração confirmada no código ou artefato correto.
- **VALIDADO:** teste adequado confirmou o resultado.
- **PAUSADO / DESCARTADO:** direção interrompida ou abandonada, mantendo histórico.

Os estados devem refletir evidência. Não avançar automaticamente de solicitado para aprovado, implementado ou validado.

## Relação entre registros

- Problemas recebem identificadores `BUG` ou `COR`.
- Oportunidades recebem identificadores `IDEA`.
- Observações positivas recebem identificadores `OBS`.
- Cada item pode apontar para prompt, commit, teste e relatório futuro.
- Quando uma correção for validada, atualizar o item original e o relatório de testes, sem apagar o histórico.

## Regra de promoção

Uma observação só deve entrar no catálogo central de ideias se representar uma direção de produto distinta e ainda não registrada. Problemas técnicos permanecem no registro de problemas. Ideias de longo prazo não devem ser transformadas em tarefas de curto prazo sem decisão explícita.
