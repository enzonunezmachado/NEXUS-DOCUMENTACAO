# Prompt-base — análise profunda de arquitetura da informação do NEXUS

> **Natureza:** prompt consolidado para novas análises independentes. Não é a transcrição literal do prompt enviado ao Claude nem uma decisão de produto.

## Contexto de entrada

Você receberá um dossiê em Markdown que descreve a visão do NEXUS, suas telas, módulos, fluxos e capacidades conhecidas. Leia o documento inteiro antes de formular uma proposta. Se houver documentos técnicos adicionais, use-os para verificar o que existe; não suponha que o dossiê substitui o código.

## Papel

Atue como uma equipe sênior integrada de:

- Estratégia e gestão de produto;
- Arquitetura da informação;
- UX research e design de interação;
- Design de sistemas e experiência adaptativa;
- Arquitetura de software, apenas para avaliar viabilidade e impactos.

Sua tarefa é produzir uma análise profunda, independente e fundamentada da arquitetura da informação e da experiência do NEXUS. Não faça uma resposta curta ou genérica. Invista o tempo necessário para investigar as relações, comparar alternativas e explicar o raciocínio.

## Entenda o produto antes de reorganizá-lo

O NEXUS não deve ser reduzido a tarefas, calendário, finanças ou produtividade genérica. É um sistema pessoal que ajuda a pessoa a compreender e coordenar o que está acontecendo, o que precisa fazer, o que está por vir e como as diferentes responsabilidades se afetam.

Seu valor está em reduzir a carga mental, conectar informações registradas uma única vez e oferecer uma experiência cotidiana concisa, agradável e confiável, sem eliminar a profundidade necessária para usuários avançados.

Sobriedade não significa frieza ou vazio. Personalidade, identidade visual, Biblioteca de Frases e outros elementos próprios do produto também fazem parte da experiência.

## Regras obrigatórias de rigor

1. **Não implemente nada.** Não altere código, documentação, banco de dados, ADRs ou roadmap.
2. **Não trate a arquitetura atual como intocável.** Questione a navegação, os agrupamentos, a hierarquia e a distribuição das telas. Tenha liberdade para propor mudanças significativas.
3. **Não invente capacidades.** Diferencie rigorosamente:
   - [ATUAL] descrito como existente no dossiê;
   - [PARCIAL] descrito como incompleto;
   - [NOVO — APRESENTAÇÃO] reorganiza algo existente;
   - [NOVO — FUNCIONALIDADE] exige capacidade nova;
   - [NOVO — DADOS/REGRA] exige alteração de modelo ou regra;
   - [HIPÓTESE] depende de inspeção, teste ou confirmação.
4. **Não confunda proposta com decisão.** Não declare que algo foi aprovado, implementado, validado ou compatível sem evidência.
5. **Não force minimalismo.** A experiência inicial deve ser simples, mas históricos, gráficos, indicadores, filtros e relações úteis precisam continuar acessíveis.
6. **Não crie uma tela única gigantesca.** Conexão entre áreas não significa colocar todos os dados em um painel.
7. **Não esconda complexidade importante.** Use camadas, hierarquia e contexto para tornar a profundidade acessível.
8. **Considere o produto real.** Se uma proposta depende de informação ausente, registre a lacuna em vez de preencher com suposição.
9. **Não confunda telas com domínios.** Investigue quais áreas criam e possuem registros, quais apenas os exibem e quais agrupam registros relacionados.
10. **Explique custos e perdas.** Toda mudança deve apresentar benefícios, riscos, trade-offs e o que fica mais difícil.

## Trabalho solicitado

### 1. Reconstrução do modelo mental

Explique com suas palavras:

- Qual problema o NEXUS resolve;
- Quais são os principais ritmos de uso;
- Quais são os domínios de informação;
- Quais registros se relacionam;
- Quais áreas são destinos, lentes, contêineres, ferramentas transversais ou manutenção.

Crie um mapa conceitual de onde cada registro nasce e em quais contextos aparece.

### 2. Diagnóstico da arquitetura atual

Avalie navegação, hierarquia, agrupamentos, nomenclatura, densidade, descoberta, consistência, duplicidade de responsabilidade, fluxos de criação e integração entre módulos.

Para cada achado, apresente evidência do dossiê, impacto no usuário, gravidade e se é fato ou hipótese.

### 3. Gere arquiteturas alternativas genuinamente diferentes

Apresente pelo menos três modelos de arquitetura, não apenas pequenas variações de nomes ou ordem das abas.

Para cada modelo, detalhe:

- Conceito e modelo mental;
- Navegação principal e secundária;
- Destino de cada módulo atual;
- Como Busca, Criar e configurações funcionam;
- Como os registros relacionados se conectam;
- Como a pessoa encontra profundidade sem sobrecarga;
- Como a arquitetura se comporta em celular e tablet;
- Benefícios, riscos e trade-offs.

Não escolha um vencedor antes de comparar as alternativas.

### 4. Desenvolva a alternativa mais promissora

Escolha a alternativa que melhor equilibra uso diário, profundidade, descoberta, identidade e coerência. Explique por que ela supera as outras e onde ainda pode falhar.

Descreva a estrutura completa das telas e subáreas. Para cada tela, informe:

- Finalidade e pergunta que responde;
- Informação prioritária;
- Hierarquia visual e conteúdo;
- Resumo e aprofundamento;
- Ações principais e secundárias;
- Filtros e modos de visualização;
- Relações com outros registros;
- Estados vazios, carregamento, erro e ausência de dados, quando relevantes;
- Comportamento no celular, tablet retrato e tablet paisagem.

Não desenhe apenas uma Home bonita. Resolva a arquitetura do produto inteiro.

### 5. Fluxos ponta a ponta

Descreva cenários realistas, por exemplo:

- Planejar uma semana com tarefas, compromissos e vencimentos;
- Criar e preparar um evento com tarefas e custos;
- Conectar um projeto a um objetivo;
- Registrar uma compra e compreender seu efeito na fatura e no orçamento;
- Investigar um gasto antigo e chegar ao evento ou projeto relacionado;
- Fazer a revisão do dia e preparar o próximo.

Mostre os passos, as telas, os dados envolvidos e como o contexto é preservado.

### 6. Adaptação a telas maiores

Não estique o layout mobile. Proponha arranjos adaptativos por contexto: rail, lista–detalhe, painel de apoio, múltiplas colunas e comportamento em paisagem. Explique quando cada padrão faz sentido e quando não faz.

### 7. Plano de transição

Separe as mudanças em:

- Organização e nomenclatura;
- Navegação;
- Apresentação visual;
- Funcionalidades novas;
- Alterações de dados ou regras.

Proponha uma sequência gradual que permita validar cada etapa e preservar a experiência funcional. Não suponha que uma reorganização visual seja sempre tecnicamente neutra.

### 8. Questões que exigem decisão humana

Liste as perguntas que não podem ser resolvidas com o dossiê sozinho. Não responda por conta própria a preferências que dependem do usuário.

## Formato da entrega

Entregue um documento estruturado em Markdown, substancial e específico. Inclua tabelas comparativas, mapas hierárquicos e diagramas Mermaid quando realmente ajudarem.

A entrega deve conter:

1. Resumo executivo;
2. Compreensão do produto;
3. Mapa conceitual;
4. Diagnóstico fundamentado;
5. Três ou mais arquiteturas alternativas;
6. Comparação com critérios explícitos;
7. Arquitetura recomendada em detalhe;
8. Mapa completo de telas;
9. Fluxos ponta a ponta;
10. Adaptação para tablets;
11. Riscos e trade-offs;
12. Plano de transição;
13. Questões abertas para decisão humana.

Seja específico. Evite slogans sem consequência prática, listas genéricas de boas práticas e elogios vazios. Não resuma o trabalho apenas para terminar rápido: a prioridade é qualidade, coerência e profundidade.

## Encerramento obrigatório

Finalize declarando claramente:

- Qual arquitetura você recomenda como hipótese de trabalho;
- Quais ideias das alternativas rejeitadas ainda merecem ser preservadas;
- Quais pontos dependem de validação no app real;
- Quais mudanças exigiriam novas funcionalidades ou alterações de dados;
- Que nenhuma proposta foi aprovada ou implementada por esta análise.