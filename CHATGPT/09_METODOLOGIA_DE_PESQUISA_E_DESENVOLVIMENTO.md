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



## 11. Mecanismo complementar de execução orientada por fases no Google AI Studio

**Estado:** possibilidade capturada para avaliação e eventual experimento; não aprovada para adoção geral e não substitui o fluxo operacional habitual.

### 11.1 Finalidade

Para trabalhos grandes, multifásicos ou que exijam muitas operações encadeadas, pode ser útil preparar antecipadamente um pacote de instruções no próprio repositório ativo do aplicativo. Em vez de depender de uma sequência de prompts escritos e revisados individualmente a cada pequena etapa, o ChatGPT e o usuário organizariam um conjunto de documentos Markdown com as fases, instruções, dependências e critérios de conclusão.

O Google AI Studio, depois de receber esses arquivos por meio de um **Pull acionado manualmente pelo usuário**, seria instruído a ler o README e seguir o plano registrado. O mecanismo funcionaria como um roteiro operacional versionado dentro do próprio projeto NEXUS.

A intenção é reduzir repetição e facilitar trabalhos extensos, sem retirar do usuário o controle e sem eliminar a possibilidade de o ChatGPT acompanhar o desenvolvimento.

### 11.2 Estrutura conceitual possível

A estrutura exata deverá ser definida quando houver um caso real. Uma organização inicial possível seria:

```text
.nexus-workflows/
└── nome-da-iniciativa/
    ├── README.md
    ├── PLANO_GERAL.md
    ├── fases/
    │   ├── 01_analise.md
    │   ├── 02_preparacao.md
    │   ├── 03_implementacao.md
    │   └── 04_validacao.md
    ├── em_andamento/
    ├── concluidas/
    └── historico/
        └── HISTORICO_DE_EXECUCAO.md
```

Essa árvore é ilustrativa, não uma estrutura já criada ou obrigatória. O pacote pode ser mais simples ou mais elaborado conforme o tamanho e o risco do trabalho. Evitar criar pastas vazias ou camadas que não tragam benefício.

### 11.3 Responsabilidade dos documentos

- **README.md:** instruções de entrada para o AI Studio. Explica o propósito do pacote, como ler a estrutura, a ordem de execução, como identificar a próxima fase, quais ações são permitidas, como registrar progresso e quando parar para pedir autorização.
- **PLANO_GERAL.md:** objetivo final, contexto, escopo, exclusões, dependências, arquitetura afetada, restrições e visão de todas as fases.
- **Documentos de fase:** cada arquivo contém objetivo, contexto necessário, instruções específicas, pré-condições, arquivos/áreas potencialmente afetados, limites, critérios observáveis de conclusão e o que fazer em caso de bloqueio.
- **em_andamento/:** opcionalmente identifica a fase atualmente iniciada, quando isso for mais claro do que um simples status no documento.
- **concluidas/:** pode receber instruções finalizadas, preservando os documentos executados como evidência do roteiro utilizado.
- **HISTORICO_DE_EXECUCAO.md:** registro cumulativo das ações realizadas pelo AI Studio: fase iniciada/concluída, resumo da implementação, arquivos criados/alterados/removidos/movidos, decisões tomadas dentro do escopo, verificações executadas, resultados, limitações, bloqueios e estado recomendado para a próxima fase.

### 11.4 Ciclo de execução proposto

1. ChatGPT e usuário entendem o trabalho e decidem se ele realmente se beneficia de execução por fases.
2. ChatGPT prepara o plano e os documentos de instrução; o usuário revisa e organiza o pacote no repositório ativo.
3. O usuário aciona **Pull** na interface do Google AI Studio para trazer os arquivos ao ambiente conectado.
4. O usuário envia um prompt inicial curto, apontando o AI Studio para o README do pacote e solicitando que leia o plano e identifique a próxima fase autorizada.
5. O AI Studio executa somente a fase permitida, respeitando os limites e critérios descritos.
6. Ao terminar, o AI Studio atualiza o estado do pacote e acrescenta ao histórico um relato suficientemente detalhado para que outra pessoa ou o ChatGPT compreenda o que ocorreu sem depender apenas da conversa original.
7. O AI Studio para no limite de autorização definido. Conforme o plano, o usuário pode responder apenas com uma confirmação como **“OK, pode prosseguir”** para liberar a fase seguinte. A confirmação não deve autorizar implicitamente mudanças fora do escopo daquela fase.
8. Quando necessário, o usuário aciona **Push** manualmente para enviar ao GitHub as atualizações do pacote e as alterações de implementação.
9. ChatGPT pode consultar os documentos e o histórico no GitHub para acompanhar o progresso, analisar desvios e orientar ajustes. O usuário também pode continuar trazendo os relatórios da conversa do AI Studio, especialmente quando houver decisões, dúvidas ou resultados que mereçam discussão.
10. O ciclo se repete até a conclusão e validação proporcional do trabalho.

### 11.5 Regras de segurança operacional e rastreabilidade

- O AI Studio só trabalha com os arquivos que chegaram ao ambiente por sincronização; o usuário continua responsável por Pull e Push.
- O README deve mandar o agente ler o plano e as instruções pertinentes antes de editar código.
- O agente não deve executar fases futuras só porque estão descritas no pacote: deve respeitar o ponto de parada e as confirmações exigidas.
- Uma confirmação libera apenas a próxima etapa delimitada, não uma ampliação irrestrita de escopo.
- Não apagar instruções concluídas sem preservar seu conteúdo. Mover para `concluidas/` ou atualizar status são opções; o histórico deve permitir reconstruir o que foi planejado e executado.
- O histórico deve ser cumulativo, claro e verificável. Não deve ser sobrescrito a cada fase nem conter apenas frases genéricas como “concluído”.
- Registrar arquivos criados, alterados, removidos e movidos, com caminhos antes/depois quando aplicável.
- Distinguir ações realizadas de ações apenas recomendadas; registrar testes e builds somente quando efetivamente executados e seus resultados conhecidos.
- Se houver ambiguidade, falha, conflito, dependência ausente ou necessidade de exceder o escopo, parar e pedir orientação em vez de improvisar uma mudança estrutural.
- Preservar a possibilidade de o usuário enviar o retorno conversacional do AI Studio ao ChatGPT. O histórico do pacote complementa essa comunicação, não a proíbe nem a substitui.
- Antes de Push, o usuário deve ter oportunidade de avaliar o que foi alterado no ambiente. O pacote não elimina a supervisão humana nem transforma o AI Studio em agente com acesso autônomo ao GitHub.

### 11.6 Quando considerar o mecanismo

Pode ser útil quando:
- a iniciativa possui várias fases naturalmente separáveis;
- as fases dependem umas das outras e precisam ser executadas em ordem;
- há muitas instruções repetitivas ou arquivos de contexto que precisariam ser enviados várias vezes;
- a continuidade precisa sobreviver a interrupções ou perda de contexto da conversa;
- o histórico de execução precisa ser consultável pelo ChatGPT diretamente no repositório.

Provavelmente não vale a pena quando:
- a tarefa é pequena e pode ser resolvida com um prompt direto;
- as etapas ainda dependem de decisões frequentes e imprevisíveis;
- preparar e manter o pacote daria mais trabalho do que executar a tarefa;
- a conversa contínua com ChatGPT e os relatórios diretos do AI Studio são mais úteis para manter o usuário no controle.

### 11.7 Natureza experimental e relação com o fluxo habitual

Este mecanismo é uma opção complementar, não uma nova regra para todo desenvolvimento. O fluxo iterativo habitual — discutir com ChatGPT, preparar um prompt, executar no AI Studio, trazer o retorno, analisar e continuar — permanece válido e preferencial para tarefas pequenas, médias ou altamente exploratórias.

A execução por fases deverá ser experimentada em um caso apropriado antes de ser considerada eficaz. A avaliação deve observar não só a quantidade de prompts, mas também o esforço total do usuário, a clareza do histórico, a qualidade das alterações, a facilidade de retomada e a capacidade do ChatGPT de compreender o que aconteceu.

Não há autorização permanente para criar essa estrutura em toda iniciativa. A decisão de utilizá-la deve ser tomada caso a caso, com o usuário.


## 12. Pesquisa sobre documentação técnica e visualização do conhecimento

Quando a necessidade envolver organização de documentos, classificar a finalidade de cada material e considerar Diátaxis (tutorial, how-to, referência e explicação). Aplicar de forma incremental, sem renomear ou fragmentar documentos existentes sem ganho claro.

Quando surgir interesse em visualizar relações, distinguir grafo de documentos Markdown de visualização do banco de dados do aplicativo. Obsidian Graph View representa notas e links; Bases usa propriedades dos arquivos. Nenhum dos dois cria automaticamente uma integração viva com Room.

Antes de propor integração:
1. definir quais relações precisam ser exploradas e por quem;
2. testar com dados fictícios e pequeno conjunto de notas;
3. comparar Obsidian com Mermaid, tabelas, MkDocs e UI nativa;
4. preservar NEXUS como fonte de verdade;
5. definir IDs, exportação, atualização, conflitos, privacidade e exclusão;
6. solicitar aprovação antes de alterar código, banco, backup ou dados reais.

Registro detalhado: [Proposta Diátaxis e Obsidian](PROPOSTAS/2026-11-26_DOCUMENTACAO_DIATAXIS_OBSIDIAN/README.md).
