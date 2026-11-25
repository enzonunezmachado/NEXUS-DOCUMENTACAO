# 04 — Interações, relações e navegação

**Estado:** EM ANÁLISE  
**Objetivo:** consolidar expectativas sobre o que acontece quando o usuário toca, expande, conclui, edita ou navega.

## 1. Nem tudo precisa ser clicável

A interface deve distinguir elementos informativos de controles e destinos. Não criar affordances falsas: algo que parece botão, checkbox, link ou campo editável precisa realizar a ação esperada.

## 2. Contrato de interação a explorar

| Elemento | Ação esperada |
|---|---|
| Título/área principal do registro | Abrir consulta/ficha específica |
| Área designada de expansão | Expandir ou recolher conteúdo |
| Checkbox de tarefa | Concluir ou alternar estado, se permitido |
| Lápis/ação de edição | Abrir formulário de edição |
| Relação com outro registro | Navegar para a tela/ficha do registro relacionado |
| “Ver todas” | Abrir coleção ou visualização completa |
| Informação puramente descritiva | Permanecer informativa, sem aparência enganosa de controle |

O contrato final deve respeitar permissões, estados, confirmações e regras de negócio existentes.

## 3. Padronização de tarefas

Foi observada uma inconsistência de expectativa:
- Na área Tarefas, há um círculo/checkbox para marcar conclusão.
- Em alguns contextos da Agenda, o marcador de tipo da tarefa parece um quadrado.
- Em outros pontos, um quadrado visualmente semelhante a controle pode não concluir, mas abrir a tarefa; a conclusão só aparece dentro do formulário.

É necessário auditar o comportamento real de cada ocorrência. A padronização desejada:
- Checkbox significa concluir/marcar.
- Ícone de tipo identifica a entidade e não deve parecer um controle de conclusão.
- Título/área de conteúdo abre a ficha.
- Edição é ação explícita.
- O mesmo registro deve manter ações equivalentes nos diferentes contextos, quando a situação permitir.

A interface não deve prometer conclusão direta onde o estado ou contexto não permitir. Nesses casos, o controle deve comunicar claramente sua função.

## 4. Relações contextuais sem excesso

O usuário quer que relações apareçam quando ajudam a entender o registro ou realizar uma ação, não que toda ficha exponha a cadeia completa de dependências.

Exemplo de evento:
- Exibir tarefas relacionadas, pois são parte relevante da organização do evento.
- Exibir projeto ou objetivo relacionado se isso for útil e suportado pela relação real.
- Permitir navegar para a tela do projeto/objetivo.
- Não abrir uma pilha de cards sobrepostos; navegar para o destino e deixar a tela anterior.

Exemplo de tarefa:
- Não precisa mostrar automaticamente projeto → objetivo → evento → todas as relações transitivas.
- Mostrar contexto imediato suficiente, com acesso a detalhes quando necessário.

## 5. Navegação entre fichas

Uma relação clicável deve levar à ficha/tela própria do destino, não abrir indefinidamente novas camadas de cards. A navegação deve manter comportamento de retorno previsível e evitar perda de estado desnecessária.

Consulta, expansão, edição e navegação são operações distintas:
- Expandir: revela mais conteúdo no contexto atual.
- Consultar: abre ficha do registro.
- Editar: abre formulário apropriado.
- Navegar: troca para a tela do registro relacionado.

## 6. Microinterações e movimento

A intenção futura inclui:
- Expansões e recolhimentos suaves.
- Deslocamento natural de elementos abaixo do card.
- Feedback tátil/visual de ações.
- Conclusão de tarefa com resposta visual clara.
- Transições entre contextos e fichas.
- Indicadores de progresso animados com moderação.

Animações sofisticadas são evolução posterior. Primeiro garantir arquitetura de interação, estados corretos, desempenho e acessibilidade. Respeitar configuração de movimento reduzido e não usar animação para atrasar ações.

## 7. Questões abertas

- Quais controles de conclusão podem atuar em cada contexto?
- Como tratar tarefas recorrentes e estados que exigem confirmação?
- Qual comportamento de retorno deve ser preservado ao navegar entre fichas?
- Quais relações são imediatas e relevantes em cada ficha?
- Quais expansões são úteis e quais criariam excesso de conteúdo?
