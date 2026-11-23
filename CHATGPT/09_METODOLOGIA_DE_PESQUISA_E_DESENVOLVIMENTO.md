# Metodologia de pesquisa e desenvolvimento de ideias do NEXUS

**Tipo:** metodologia de trabalho da memória estratégica  
**Estado:** vigente como processo documental; não substitui governança técnica do repositório do aplicativo.

## 1. Objetivo

Criar um caminho claro entre perceber uma necessidade e implementar uma solução. Nem toda ideia já nasce tecnicamente compreendida. Algumas precisam de pesquisa, comparação de referências, experimentação visual ou investigação do código antes de ser possível escrever uma especificação útil.

A documentação deve permitir que o NEXUS avance sem transformar dúvidas em requisitos prematuros, sem enviar instruções vagas ao Google AI Studio e sem confundir pesquisa com autorização para alterar o aplicativo.

## 2. Ciclo de vida

| Estado | Significado | O que registrar | O que ainda não significa |
|---|---|---|---|
| CAPTURADA | A necessidade, ideia, observação ou problema foi registrado | Origem, contexto, objetivo, evidências e dúvidas iniciais | Não é aprovação nem prioridade |
| EM PESQUISA | Faltam informações para entender caminhos possíveis | Fontes, práticas de referência, tecnologias, exemplos, limitações e perguntas | Não é decisão técnica |
| EM ANÁLISE | Há material suficiente para comparar alternativas | Benefícios, custos, riscos, dependências, impactos e conflitos | Não é autorização para implementar |
| PROPOSTA | Uma direção concreta foi formulada | Solução recomendada, alternativas, escopo, critérios de aceite e pendências | Não é aprovação |
| APROVADA | O usuário decidiu seguir com a direção | Decisão explícita, limites, condições e data | Não significa que esteja pronta para desenvolvimento |
| PLANEJADA | O escopo de execução foi organizado | Etapas, arquivos/módulos envolvidos, dependências, riscos e validação | Não significa que o código foi alterado |
| EM IMPLEMENTAÇÃO | O trabalho foi autorizado e iniciado | Escopo autorizado, ambiente, mudanças em andamento e desvios | Não significa que terminou ou passou nos testes |
| IMPLEMENTADA | A mudança está presente no código/documentação técnica | Evidência no repositório, commit/versão e resumo do que mudou | Não significa validação de uso |
| VALIDADA | A mudança passou pela verificação adequada | Testes executados, ambiente, resultado e limitações restantes | Não significa ausência absoluta de defeitos |
| PAUSADA | O trabalho foi interrompido temporariamente | Motivo conhecido, estado atual e condição para retomada | Não significa rejeição |
| REJEITADA | Uma proposta foi avaliada e não escolhida | Motivo e alternativa, quando houver | Não significa apagar o histórico |
| DESCARTADA | A ideia não seguirá adiante | Motivo, quando conhecido, e referências | Não significa que nunca possa ser reaberta |

Os estados podem avançar, voltar ou ser reabertos. Registrar transições importantes sem apagar o histórico.

## 3. Quando usar cada etapa

- Uma observação simples pode permanecer CAPTURADA até ser priorizada.
- Se a solução depende de descobrir como outras aplicações resolvem um problema, mover para EM PESQUISA.
- Quando as principais alternativas e consequências estiverem compreendidas, mover para EM ANÁLISE.
- Quando houver uma direção recomendada com escopo claro, registrar PROPOSTA.
- Somente após decisão explícita do usuário, registrar APROVADA.
- Antes de enviar instruções ao Google AI Studio, delimitar o trabalho em PLANEJADA e declarar o que pode e o que não pode ser alterado.
- Após alterações, verificar o código e a documentação técnica para registrar IMPLEMENTADA.
- Registrar VALIDADA somente com evidência de testes adequados.

## 4. Estrutura recomendada para registros de pesquisa

Cada pesquisa relevante deve conter:

1. **Pergunta central:** o que precisamos descobrir?
2. **Motivo:** por que a decisão depende dessa informação?
3. **Contexto do NEXUS:** telas, módulos, arquitetura e restrições envolvidos.
4. **Fontes:** documentação oficial, aplicações de referência, estudos, exemplos e data de consulta.
5. **Práticas encontradas:** como outras soluções abordam o problema.
6. **Aplicabilidade:** o que pode ou não ser transferido para o NEXUS.
7. **Alternativas:** caminhos possíveis, incluindo não fazer nada.
8. **Impactos:** UX, acessibilidade, manutenção, desempenho, privacidade, compatibilidade e custo, conforme aplicável.
9. **Dúvidas restantes:** o que ainda impede uma proposta?
10. **Conclusão provisória:** o que a pesquisa sugere, sem fingir que já existe decisão.

Fontes externas devem ser identificadas por nome e URL. Distinguir recomendações oficiais, exemplos observados e interpretação própria.

## 5. Estrutura recomendada para propostas

Uma proposta deve explicar:

- problema e resultado desejado;
- experiência atual e evidência;
- direção recomendada;
- alternativas consideradas e motivos para não escolhê-las;
- escopo incluído e exclusões;
- telas, componentes e módulos afetados;
- dependências técnicas a confirmar;
- riscos e impactos;
- critérios de aceite observáveis;
- plano de validação;
- decisões que ainda dependem do usuário.

## 6. Regras de autorização

- Capturar uma ideia não autoriza pesquisa externa ilimitada nem implementação.
- Solicitar pesquisa não autoriza alterar o código.
- Pesquisar APIs não autoriza adicionar dependências.
- Uma proposta não é decisão.
- Aprovar uma direção não autoriza ampliar o escopo além do que foi aprovado.
- Atualizar documentação estratégica não equivale a atualizar a documentação técnica do aplicativo.
- Google AI Studio deve receber instruções claras sobre o estado do trabalho, as fontes acessíveis, os limites e as ações permitidas.
- Nunca declarar implementação, build ou validação sem evidência correspondente.

## 7. Organização dos documentos

- `03_IDEIAS_E_BRAINSTORMS.md`: catálogo/índice de ideias; não duplicar nele relatórios completos.
- `06_PENDENCIAS_E_DIRECAO_FUTURA.md`: quadro de acompanhamento e próximos passos.
- `07_HISTORICO_DE_REGISTROS.md`: registro cronológico das consolidações e mudanças relevantes.
- `PROPOSTAS/`: documentos de direção, pesquisa consolidada e propostas específicas.
- `TESTES/`: evidências, cenários, observações e resultados de testes.
- `09_METODOLOGIA_DE_PESQUISA_E_DESENVOLVIMENTO.md`: regra de processo e ciclo de vida.

Uma ideia deve ter uma fonte detalhada principal. Os demais documentos devem apontar para ela, evitando cópias divergentes.

## 8. Relação com GOOGLE_IA e documentação técnica

CHATGPT mantém intenção, contexto, pesquisa e decisões estratégicas. O repositório ativo do aplicativo mantém código e documentação técnica vigente. Os artefatos internos do Google AI Studio são complemento operacional.

Quando uma proposta for aprovada e encaminhada:
1. registrar o prompt enviado e o escopo autorizado;
2. indicar quais documentos técnicos o agente deve consultar;
3. exigir que limitações de acesso sejam declaradas;
4. conferir o resultado no código/repositório;
5. atualizar e espelhar documentação técnica somente conforme o impacto real;
6. registrar evidências de build e testes.

## 9. Exemplo aplicado — IDEA-006

A melhoria dos layouts adaptativos e da Agenda semanal está em EM PESQUISA / EM ANÁLISE. Já existe feedback de uso real e uma pesquisa inicial nas diretrizes oficiais do Android. Ainda é necessário inspecionar a implementação atual, comparar a Agenda com o Kanban, prototipar a rolagem independente dos dias e avaliar diferentes dimensões de janela. Só depois disso deve ser elaborada uma proposta para aprovação. Nenhuma implementação adicional está autorizada por este registro.
