# PROMPT — Diagnóstico de rotação e exploração da identidade visual NEXUS

**Identificador:** NEXUS-AI-2026-11-21-ROTACAO-IDENTIDADE-01  
**Data:** 21/11/2026  
**Estado:** aprovado para encaminhamento; ainda não enviado ao Google AI Studio  
**Escopo:** diagnóstico técnico do BUG-001 + exploração visual isolada da identidade tipográfica NEXUS  
**Autorização:** análise e protótipos exploratórios isolados. Não autoriza alterações definitivas no aplicativo.

---

## Contexto

Estamos evoluindo o NEXUS, um aplicativo Android nativo, Kotlin, Jetpack Compose, Material 3, Room e offline-first. Antes de qualquer alteração, consulte o código e a documentação técnica atuais do próprio projeto. Não presuma que informações históricas deste prompt descrevem a implementação atual.

Este pedido reúne duas frentes independentes:

1. Investigar um defeito funcional recorrente relacionado à rotação do dispositivo.
2. Explorar uma direção de identidade visual tipográfica para a marca NEXUS.

Também há observações positivas do teste real do perfil fictício Gabriel que devem ser preservadas durante qualquer trabalho futuro.

## PARTE A — BUG-001: perda de estado ao girar o dispositivo

### Comportamento relatado

Ao criar ou editar registros, girar o telefone pode fazer o aplicativo retornar à página inicial e perder o conteúdo digitado ou o progresso da edição. O problema foi percebido em diferentes abas e fluxos, não apenas em Eventos. Também foram mencionados formulários, edição de dados e menu de três pontos.

### Resultado esperado

A rotação/recriação da Activity deve preservar, conforme aplicável:

- tela e rota atuais;
- conteúdo digitado em formulários;
- progresso de criação ou edição;
- seleções e filtros relevantes;
- diálogos, menus e estados transitórios que devam sobreviver à rotação.

### Trabalho solicitado nesta etapa

Faça uma investigação técnica baseada no código atual. Examine, conforme aplicável:

- ciclo de vida e recriação da Activity;
- estado de navegação e restauração da rota;
- uso de `remember`, `rememberSaveable`, ViewModel e `SavedStateHandle`;
- formulários, editores, diálogos, menus e seleções;
- padrões compartilhados entre abas e componentes;
- limites de tamanho e tipos de estado que podem ser salvos.

Identifique a causa ou causas prováveis com evidências concretas: arquivos, classes, funções e fluxos envolvidos. Se a causa ainda não puder ser determinada, explique o que falta observar.

Apresente uma proposta de correção abrangente, priorizada e compatível com a arquitetura existente. Não faça um workaround apenas para Eventos se o defeito for sistêmico.

Diferencie claramente:
- rotação/recriação da Activity;
- recriação do processo pelo Android;
- encerramento forçado pelo usuário.

Explique quais cenários a solução proposta cobriria e quais não cobriria. Sugira testes proporcionais ao risco, incluindo um roteiro manual reproduzível.

**Limite desta etapa:** não implemente a correção no código de produção, não altere navegação, arquitetura, banco, migrations ou telas existentes. Primeiro apresente diagnóstico, evidências, alternativas e plano de implementação para revisão.

## PARTE B — identidade visual tipográfica NEXUS

### Direção desejada

Queremos explorar um wordmark original para a palavra **NEXUS**, com identidade monocromática, geométrica, futurista e elegante.

A imagem de referência enviada pelo usuário mostra a palavra HUMANICA com letras construídas por segmentos, cortes, separações e espaços negativos. Ela serve exclusivamente como referência de linguagem visual. **Não reproduza a fonte, o desenho das letras, a composição, nem faça uma versão disfarçada da referência.** Crie soluções originais para NEXUS.

Características a explorar:

- letras com construção geométrica própria;
- segmentos separados que, em conjunto, formam letras reconhecíveis;
- cortes, intervalos e espaços negativos usados com intenção;
- proporções e espaçamentos equilibrados;
- personalidade marcante sem prejudicar a leitura;
- preto e branco como base, sem verde na identidade tipográfica;
- desenho que funcione em tamanhos grandes e pequenos.

Não é necessário criar uma família tipográfica completa. O objetivo inicial é um wordmark exclusivo NEXUS e, se fizer sentido, um conjunto limitado de estilos de destaque compatíveis com ele.

### Relação com a identidade atual

O aplicativo atualmente utiliza um ícone com N branco sobre fundo preto. A direção discutida é preservar esse ícone nesta etapa. Não redesenhe, substitua ou altere o ícone do aplicativo sem autorização separada.

O problema registrado como COR-001 é a presença de um N verde redundante antes da palavra NEXUS, criando uma leitura visual duplicada. A intenção é estudar uma solução de identidade completa para o nome, não simplesmente apagar o N verde e deixar uma palavra sem personalidade.

### Três direções para explorar

Apresente pelo menos três propostas originais e visualmente distintas:

1. **Geométrica contemporânea:** limpa, precisa, minimalista e tecnológica.
2. **Editorial sofisticada:** proporções e detalhes tipográficos mais elegantes, sem perder modernidade.
3. **Experimental segmentada:** cortes, segmentos e espaços negativos mais expressivos, com legibilidade preservada.

Para cada proposta, mostre o wordmark NEXUS em preto sobre branco e branco sobre preto. Explique a lógica de construção das letras, legibilidade, personalidade e limitações em tamanhos pequenos.

### Aplicação na interface — experimentação, não decisão definitiva

Queremos testar uma tipografia de destaque em elementos selecionados, não aplicar uma fonte futurista em todo o aplicativo.

Experimente visualmente, se possível em protótipos isolados:

- saudação, como “Bom dia.” ou “Boa tarde.”;
- títulos de seções, como “Tarefas do mês”;
- nome da pessoa, como “Gabriel Ferreira”, como comparação opcional;
- títulos ou indicadores principais da Home.

Compare pelo menos três hierarquias:
- saudação e nome com destaque;
- somente títulos de seção com destaque;
- saudação com destaque, mantendo nome e informações funcionais na tipografia padrão.

A tipografia funcional do restante do aplicativo deve continuar confortável e legível. Descrições, formulários, nomes, valores, listas e informações densas não devem receber estilização especial automaticamente.

### Personalização: avaliar antes de construir

A ideia é experimentar variações durante o desenvolvimento até escolher uma direção. Isso não significa que o aplicativo final precise oferecer um editor complexo de tipografia.

Avalie a diferença entre:
- manter uma identidade única e consistente no produto final;
- oferecer, futuramente, uma opção simples de ativar/desativar tipografia de destaque em áreas compatíveis;
- permitir personalização individual de cada elemento, alternativa que não deve ser presumida como desejável.

Não implemente configurações de personalização nesta etapa. Recomende a abordagem mais simples e coerente após apresentar os protótipos.

### Limites desta etapa visual

- Não altere a UI de produção.
- Não substitua fontes globais do aplicativo.
- Não aplique o wordmark em todas as telas.
- Não altere o ícone atual.
- Não remova definitivamente recursos antes de mostrar as alternativas.
- Não copie a referência HUMANICA.
- Não adicione verde à nova direção tipográfica.

Crie os estudos de forma isolada, sem conectá-los à navegação ou substituir componentes de produção. Se o ambiente não permitir renderizar protótipos isolados, apresente alternativas visuais comparáveis por um meio que consiga produzir e explique a limitação, sem improvisar mudanças definitivas no app.

## PARTE C — comportamentos positivos que devem ser preservados

Durante a exploração manual do backup de Gabriel, o usuário relatou uma experiência inicial positiva. Não é uma auditoria completa do aplicativo, mas estes pontos foram elogiados:

### Agenda
- Organização geral considerada boa.
- Fluxo de selecionar uma data e consultar os itens correspondentes abaixo foi considerado claro.
- Distribuição moderada e plausível dos registros — aproximadamente duas tarefas por dia e eventos ocasionais — pareceu mais realista e menos artificialmente carregada.

### Alertas na Home
- Vermelho para atrasos e pendências vencidas.
- Amarelo para conflitos e sobreposição de horários.
- A distinção semântica entre os dois estados foi considerada útil e deve ser preservada.

### Cuidado geral
Não simplifique ou remova comportamentos apreciados apenas para acomodar a nova identidade visual. A tipografia deve se adaptar à interface, e não prejudicar hierarquia, legibilidade ou compreensão dos alertas.

A preferência por abrir a Agenda inicialmente na visão semanal foi registrada como uma direção de evolução separada. Não a implemente neste trabalho nem a misture ao BUG-001.

## PARTE D — entregáveis desta etapa

Antes de qualquer implementação definitiva, entregue:

1. Diagnóstico do BUG-001, com evidências no código e arquivos envolvidos.
2. Causa confirmada ou hipóteses ordenadas por confiança, deixando explícito o que ainda não foi comprovado.
3. Plano de correção proposto, riscos, impacto arquitetural e testes recomendados.
4. Pelo menos três propostas originais de wordmark NEXUS, com variações preto/branco.
5. Comparação das propostas aplicadas aos exemplos de interface indicados.
6. Recomendação sobre identidade única versus opção limitada de tipografia de destaque.
7. Lista objetiva do que seria alterado em uma etapa futura, separando correção funcional de identidade visual.

## Governança e restrições obrigatórias

- Consulte a documentação técnica atual e o código antes de concluir.
- Código é a fonte da verdade sobre a implementação existente.
- Não invente nomes de arquivos, componentes, testes executados ou resultados.
- Não altere banco, entidades, DAOs, migrations, backup, regras financeiras, navegação ou módulos fora do escopo.
- Não faça commits, publique APKs ou modifique workflows.
- Não implemente mudanças definitivas nesta etapa.
- Não transforme ideias futuras do catálogo em escopo deste pedido.
- Preserve os ADRs e padrões arquiteturais existentes.
- Se encontrar conflito entre este pedido e uma decisão técnica documentada, apresente o conflito em vez de ignorá-lo.
- Após a análise, aguarde revisão e autorização explícita antes de implementar.

## Documentação e continuidade

Ao concluir a análise, prepare um relatório objetivo com:
- o que foi inspecionado;
- o que foi descoberto;
- o que é evidência e o que é hipótese;
- alternativas consideradas;
- arquivos que seriam afetados;
- testes propostos e limitações;
- protótipos visuais produzidos e onde estão.

Não marque BUG-001 ou COR-001 como corrigidos. Não marque nada como implementado ou validado.

Se a etapa produzir artefatos documentais no projeto, atualize os documentos técnicos afetados somente se houver mudança técnica real — nesta etapa, não se espera alteração do código de produção. Mantenha a documentação e os artefatos internos de continuidade coerentes, incluindo NEXUS_CONTINUIDADE, sem substituir o histórico.

**Ao final, pare e aguarde a avaliação do usuário.**
