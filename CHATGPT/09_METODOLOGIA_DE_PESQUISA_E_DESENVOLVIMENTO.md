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

## 10. Coordenação operacional entre ChatGPT, Google AI Studio e GitHub

### 10.1 O papel do usuário no processo real

O usuário não é apenas alguém que copia um prompt, aperta sincronização e instala um APK. Ele exerce a coordenação operacional e funciona como ponte humana entre ambientes que não compartilham automaticamente contexto, estado de trabalho ou capacidade de ação.

Na prática, sua participação pode incluir:

- desenvolver e amadurecer ideias com o ChatGPT, discutir problemas, levantar hipóteses e decidir o que deve ser pesquisado ou implementado;
- receber prompts, planos, análises e documentos preparados pelo ChatGPT e transportá-los para o Google AI Studio;
- recolher respostas, relatórios, diagnósticos e resultados do AI Studio e trazê-los de volta ao ChatGPT para interpretação;
- organizar, criar, copiar e posicionar arquivos Markdown e outros materiais nos ambientes corretos;
- quando uma ferramenta trava, perde contexto ou não consegue executar o fluxo esperado, preparar arquivos de continuidade, reconstruir o contexto, orientar novamente a leitura dos documentos e encontrar caminhos alternativos;
- controlar a sincronização com o GitHub pela interface do AI Studio;
- acompanhar o que foi alterado, avaliar os resultados, instalar APKs, explorar o aplicativo em uso real, identificar problemas e comunicar evidências;
- tomar decisões de produto e coordenar a passagem entre pesquisa, planejamento, implementação e validação.

Esse trabalho de coordenação, transporte de informação, recuperação de contexto e resolução de obstáculos é parte integrante do processo de desenvolvimento. Não deve ser tratado como esforço invisível ou como uma etapa trivial.

### 10.2 Limites reais do Google AI Studio e sincronização GitHub

O Google AI Studio trabalha sobre os arquivos disponíveis no seu ambiente de projeto conectado. Ele não deve ser tratado como um agente com acesso autônomo ao GitHub: não navega livremente pelo repositório remoto, não decide quando sincronizar e não aciona Pull ou Push por conta própria. Também não é responsável por criar commits de forma autônoma.

A sincronização disponível é operada manualmente pelo usuário na interface:

- **Pull:** o usuário aciona o botão; o conteúdo remoto do repositório conectado é trazido para atualizar os arquivos do ambiente de trabalho do AI Studio.
- **Push:** o usuário aciona o botão; as alterações presentes no ambiente de trabalho são enviadas ao repositório conectado.

Portanto, não atribuir ao AI Studio a iniciativa ou o controle dessas operações. É o usuário quem decide quando fazer Pull ou Push e quem efetivamente aciona os comandos. O AI Studio pode trabalhar nos arquivos que estão no ambiente após a sincronização, mas isso não equivale a acesso independente ao GitHub.

A integração considerada neste processo está vinculada ao repositório ativo `enzonunezmachado/PROJETO-NEXUS-`. Não presumir que ela também sincronize o repositório documental `NEXUS-DOCUMENTACAO` ou o ATLAS.

### 10.3 Fluxo operacional habitual

O fluxo mais comum é uma colaboração iterativa, não uma automação contínua:

1. O usuário apresenta uma ideia, necessidade, problema ou evidência ao ChatGPT.
2. ChatGPT ajuda a pesquisar, analisar, planejar, decidir e preparar instruções proporcionais ao trabalho.
3. O usuário transporta o prompt ou material para o Google AI Studio.
4. O AI Studio lê e analisa os arquivos disponíveis no ambiente conectado e executa o escopo solicitado, quando possível.
5. O usuário recolhe o retorno do AI Studio e o traz ao ChatGPT.
6. ChatGPT ajuda a avaliar o resultado, conferir coerência, identificar lacunas e preparar correções ou próximos passos.
7. Quando necessário, o usuário aciona **Push** na interface do AI Studio para enviar as alterações ao repositório GitHub.
8. O GitHub Actions pode compilar, executar verificações e publicar o APK conforme o workflow existente.
9. O usuário acompanha o resultado, instala o APK quando disponível, testa no dispositivo e traz as observações para nova análise.
10. Documentação, decisões e próximos passos são atualizados conforme o impacto real.

As etapas podem variar. Nem todo trabalho precisa percorrer todas elas, e a ordem pode exigir ajustes conforme a situação.

### 10.4 Desvios, improvisos e recuperação do fluxo

O fluxo acima descreve o caminho habitual, não uma garantia de que tudo funcionará linearmente. Na prática, limitações das ferramentas, perda de contexto, falhas de sincronização, respostas incompletas, interrupções ou tarefas maiores que um único prompt podem obrigar o usuário a executar atividades adicionais.

Exemplos incluem reconstruir instruções, criar arquivos intermediários, separar ou reorganizar materiais, repetir operações, investigar por que uma etapa não funcionou, transportar conteúdo manualmente e adaptar o plano para continuar. Essas ações não são exceções irrelevantes: frequentemente são necessárias para tornar o processo viável.

Ao propor um fluxo novo, considerar explicitamente:
- o esforço de coordenação que recairá sobre o usuário;
- quais ações dependem de intervenção manual;
- quais informações precisam ser transportadas entre ambientes;
- como retomar o trabalho quando uma ferramenta perde contexto ou falha;
- como evitar repetição, ambiguidade e tarefas auxiliares desnecessárias.

Não pressupor que basta enviar um prompt para que todo o trabalho aconteça, nem desenhar processos que dependam de capacidades que o AI Studio não possui. A solução deve reduzir a carga operacional do usuário, não transferir para ele uma quantidade desproporcional de burocracia.

### 10.5 Princípio de colaboração

ChatGPT contribui com raciocínio, pesquisa, planejamento, análise, documentação e preparação de instruções. Google AI Studio trabalha nos arquivos disponíveis em seu ambiente e implementa o que consegue dentro dos limites apresentados. GitHub mantém o repositório, histórico e automações configuradas. O usuário coordena a continuidade, controla as operações manuais, transporta informações, toma decisões e valida o comportamento real.

Nenhum desses papéis deve ser confundido com os demais. Sempre declarar o que foi efetivamente feito, por quem e com qual evidência.

