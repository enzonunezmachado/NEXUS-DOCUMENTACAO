# 06 — Evolução conceitual e arquitetural da reformulação

**Data do registro:** 25/11/2026  
**Estado:** DOCUMENTO DE RASTREABILIDADE CONCEITUAL  
**Escopo:** explicar a evolução entre a primeira consolidação da iniciativa ATUALIZAÇÃO e a direção refinada nos documentos 00–05.

Este documento não é uma nova proposta de telas nem uma decisão técnica. Ele explica por que a documentação foi reorganizada, quais ideias foram mantidas, quais foram refinadas e quais simplificações foram feitas para evitar redundância ou conclusões prematuras.

## 1. Síntese da evolução

A iniciativa começou como uma consolidação de ideias para uma reformulação ampla da experiência do NEXUS, combinando a proposta de arquitetura da informação do Claude com conversas sobre identidade visual, cards, fichas, relações e interações.

A evolução mais importante foi perceber que a necessidade não é apenas reorganizar as abas ou modernizar componentes. O NEXUS precisa de uma linguagem de produto mais madura, capaz de apresentar informações de naturezas diferentes com personalidade, proporção e clareza, enquanto preserva a profundidade e a integridade do sistema.

Em paralelo, ficou mais claro que a proposta de cinco destinos do Claude é uma hipótese útil, mas não pode se tornar a estrutura presumida antes de compreender as entidades, relações, áreas e fluxos reais do aplicativo.

A revisão, portanto, desloca o centro da iniciativa de “adotar uma nova arquitetura e redesenhar as telas” para **investigar o produto real, esclarecer o modelo mental e então decidir a melhor experiência de ponta a ponta**.

## 2. O que foi consolidado

### 2.1 Reformulação ampla da experiência, não reescrita automática do aplicativo

**Antes:** a palavra reconstrução poderia ser interpretada como uma mudança ampla também no núcleo técnico.

**Agora:** reconstrução significa repensar a apresentação, a organização e a interação do sistema. Alterações em dados, regras, persistência ou arquitetura de software continuam possíveis, mas precisam ser justificadas separadamente.

**Motivo:** evitar que uma iniciativa de UX seja confundida com autorização para reescrever o app ou substituir tecnologias.

### 2.2 Personalidade visual mais expressiva, sem copiar o Luma

**Antes:** a referência de “15% da essência do Luma” aparecia como direção geral de inspiração.

**Agora:** a inspiração foi traduzida em atributos observáveis: protagonismo do conteúdo, capas úteis, cards bem compostos, hierarquia, cores intencionais, degradês controlados e microinterações. A identidade do NEXUS continua sóbria, tecnológica e própria.

**Motivo:** transformar uma referência estética subjetiva em critérios que possam ser discutidos e avaliados sem copiar o produto de referência.

### 2.3 Personalidade por domínio, com sistema visual compartilhado

**Antes:** havia a intenção de que diferentes áreas tivessem personalidade própria.

**Agora:** essa intenção foi delimitada: a expressão varia por composição, hierarquia e acentos adequados à finalidade, sem criar um tema independente para cada aba.

**Motivo:** equilibrar diferenciação funcional e reconhecimento de que tudo pertence ao mesmo produto.

### 2.4 Densidade adaptativa como princípio central

**Antes:** havia observações sobre cards compactos, telas vazias e listas numerosas.

**Agora:** a densidade é tratada como uma decisão contextual baseada na natureza do registro, volume de dados, importância e ação esperada. Tarefas continuam compactas; eventos e projetos podem receber maior protagonismo; componentes pequenos não devem herdar bordas ou dimensões exageradas.

**Motivo:** evitar tanto a aparência de planilha quanto a transformação de tudo em cards grandes.

### 2.5 Distinção entre tipo, estado, relação e ação

**Antes:** a discussão sobre cores e símbolos apontava o risco de uma cor representar simultaneamente atraso e vínculo com projeto.

**Agora:** a documentação explicita quatro significados que precisam ser diferenciados:
- Tipo: o que é o registro.
- Estado: em que condição operacional ele está.
- Relação/contexto: a que outros registros ele se conecta.
- Ação: o que acontece ao interagir com o elemento.

**Motivo:** reduzir ambiguidades e impedir que um marcador contextual seja confundido com alerta ou controle.

### 2.6 Interação como contrato, não apenas aparência

**Antes:** já existia a intenção de diferenciar consulta, edição, expansão e conclusão.

**Agora:** o contrato foi explicitado também para navegação e exclusão, incluindo a expectativa de que checkbox conclua, título consulte, lápis edite e relação navegue para o destino canônico.

**Motivo:** transformar impressões de UX em comportamentos verificáveis.

## 3. O que foi reformulado ou deixou de ser presumido

### 3.1 Os cinco destinos do Claude deixaram de funcionar como estrutura-base presumida

**Formulação anterior:** Hoje, Agenda, Tarefas, Planos e Dinheiro apareciam como hipótese de referência principal.

**Formulação atual:** a proposta continua preservada e será comparada com outras arquiteturas. Não se assume que cinco destinos sejam melhores, que “Planos” seja o nome certo ou que Eventos devam ser agrupados dessa forma.

**Motivo:** evitar ancoragem prematura em uma proposta ainda não validada contra o aplicativo real, o modelo mental e a descoberta de funcionalidades.

### 3.2 “Planos” foi rebaixado de possível nome de destino para hipótese de agrupamento

A documentação passa a usar “Planejamento” como nome provisório ao descrever uma possível área, sem afirmar que esse será o nome final ou que Objetivos, Projetos e Eventos formam uma hierarquia única.

**Motivo:** o agrupamento pode ser útil, mas o nome e a relação entre as entidades precisam ser compreendidos primeiro.

### 3.3 A hierarquia de entidades deixou de ser tratada como árvore presumida

A documentação agora destaca que tarefas podem ter múltiplos vínculos e que Projeto, Objetivo, Evento e Meta não devem ser organizados em uma cadeia fixa sem verificar modelo e código.

**Motivo:** relações técnicas e relações conceituais não são necessariamente hierarquia. Uma associação não prova subordinação.

### 3.4 Mercado foi separado conceitualmente de Dinheiro

Mercado é tratado como ambiente de informações externas; Dinheiro, como domínio das finanças pessoais. A proximidade entre ambos não justifica fundi-los.

**Motivo:** evitar que dados de mercado sejam confundidos com patrimônio, orçamento ou lançamentos pessoais.

### 3.5 A referência visual deixou de ser uma autorização genérica para “deixar mais bonito”

A inspiração do Luma foi decomposta em princípios de composição e qualidade, com ressalvas sobre identidade própria, proporção, acessibilidade e finalidade de cada área.

**Motivo:** reduzir subjetividade e evitar cópia superficial ou excesso de elementos decorativos.

## 4. O que foi reduzido por redundância

A revisão também reorganizou conteúdo repetido para que cada documento tenha uma responsabilidade principal.

| Conteúdo que aparecia repetidamente | Tratamento atual |
|---|---|
| “Não transformar tudo em cards” | Princípio central em 03; referenciado nos demais apenas quando necessário |
| Consulta, edição, expansão e conclusão | Contrato detalhado em 04; 03 trata apenas da apresentação |
| Cores de status versus cores de vínculo | Centralizado em 02; 03 e 04 tratam somente dos efeitos nos componentes e ações |
| Proposta Claude de cinco destinos | Contextualizada em 01; não repetida como se fosse arquitetura escolhida |
| Luma e personalidade visual | Direção principal em 00 e critérios visuais em 02 |
| Auditoria, fases e critérios de aceite | Concentrados em 05 |
| Autoridade documental e ausência de autorização de implementação | Governança em 00 e índice em README; demais documentos mantêm apenas avisos curtos de estado |

A redução não significa descartar os assuntos. Significa manter a explicação completa no documento responsável e evitar versões ligeiramente diferentes da mesma regra em vários lugares.

## 5. O que foi preservado sem mudança de direção

- A visão do NEXUS como sistema pessoal para reduzir carga mental e conectar responsabilidades.
- O princípio de registrar uma vez e apresentar o mesmo registro em contextos relevantes.
- A necessidade de profundidade progressiva, sem reduzir o produto a uma interface minimalista.
- A preservação de históricos, análises, filtros e informações avançadas.
- A qualidade da visão semanal atual como referência interna positiva.
- A necessidade de fichas especializadas por entidade.
- A expansão contextual seletiva, sem pilhas infinitas de cards.
- A separação entre consulta e edição.
- A navegação para a ficha canônica de registros relacionados.
- A preservação das semânticas existentes até auditoria do Design System e do código.
- A adaptação real para tablets e diferentes orientações.
- A exigência de validar tudo no aplicativo Android real.

## 6. O que continua em aberto

A revisão não resolveu — e não deve fingir resolver — os seguintes pontos:

1. Arquitetura final da navegação e quantidade de destinos.
2. Nome e escopo de uma possível área de Planejamento.
3. Definições exatas de Objetivo, Projeto, Meta e Evento.
4. Representação visual de relações e múltiplos vínculos.
5. Matriz final de cores, estados, categorias e seleção.
6. Inventário e seleção final de ícones.
7. Quais cards devem expandir e quais devem abrir fichas.
8. Quais relações aparecem em cada tipo de ficha.
9. Quais mudanças exigem apenas apresentação e quais afetam dados, regras ou navegação.
10. Sequência definitiva de implementação.

## 7. Impacto na organização dos documentos

Os documentos 00–05 foram revisados para reduzir sobreposição e dar uma responsabilidade clara a cada um:

- **00:** por que a reformulação existe, seus limites e sua governança.
- **01:** como investigar domínios, entidades, destinos e arquitetura da informação.
- **02:** como construir linguagem visual, semântica de cores e símbolos.
- **03:** como apresentar coleções e entidades por meio de componentes e fichas.
- **04:** como as ações, relações e navegação devem ser compreendidas.
- **05:** em que ordem investigar, validar e eventualmente implementar.

Este documento 06 explica a evolução entre versões e registra a justificativa das mudanças. Ele não deve duplicar os requisitos detalhados dos documentos temáticos.

## 8. Regra para evoluções futuras

Quando uma nova conversa alterar uma direção registrada:
1. Atualizar o documento temático responsável.
2. Verificar se README e outros documentos ficaram contraditórios.
3. Registrar aqui a mudança conceitual relevante, o motivo e o que foi substituído ou preservado.
4. Manter questões não resolvidas como abertas.
5. Não alterar documentação técnica oficial nem código até que exista decisão e autorização próprias.

**Síntese:** a evolução não abandonou a proposta de arquitetura do Claude nem a visão visual mais expressiva. Ela colocou ambas no lugar correto: referências e hipóteses a serem avaliadas dentro de uma compreensão mais completa do NEXUS, de suas entidades, regras e experiência real.
