# NEXUS — Etapa 3: Propostas de Produto, Arquitetura da Experiência e Exploração de Profundidade

**Papel:** Equipe Multidisciplinar Sênior (Estratégia de Produto, Arquitetura de Informação, Design de Sistemas UX/UI e Engenharia de Software)  
**Data:** Novembro de 2026  
**Status:** Documento Oficial de Concepção, Arquitetura e Especificação de Protótipos (Etapa 3)  
**Diretriz Operacional:** Não altera código Android em produção, não modifica ADRs vigentes sem aprovação formal e preserva rigorosamente as decisões consolidadas (Design System Monocromático, Monólito N-1, Room v6 aditivo e Offline-first).

---

# PARTE 1 — A NOVA BASE FILOSÓFICA CONSOLIDADA

A evolução da visão do NEXUS atinge aqui sua maturidade definitiva: superamos a falsa dicotomia entre *"minimalismo vazio que esconde dados"* e *"painel corporativo denso que gera exaustão cognitiva"*.

### 1.1 A Tese Central: Conexões Vivas e Consciência Real
O ser humano não vive a sua existência em gavetas estanques. Na vida real:
- Uma viagem não é apenas uma data na folha do calendário: ela é um centro de gravidade que demanda tarefas de preparação prévia, consome limites em faturas de cartão de crédito, envolve passagens/hospedagem e bloqueia o tempo útil antes e depois.
- Uma despesa no mercado não é apenas um número debitado: ela afeta o orçamento da categoria, o saldo líquido projetado e a disponibilidade financeira para os compromissos da semana.
- Um compromisso médico pode gerar exames preparatórios (tarefas), pagamento em dinheiro/Pix (finanças) e afetar a disponibilidade da tarde.

O NEXUS não é um agregador passivo de listas, mas sim o **Sistema de Conexões Vivas da Vida Real**.

### 1.2 Os Três Pilares da Experiência
1. **Conciso e Agradável no Cotidiano:** A navegação imediata é fluida e acolhedora. A pessoa consulta em segundos o que importa agora sem fricção.
2. **Profundidade em Camadas (*Progressive Disclosure* sem Ocultação Artificial):** A complexidade não é varrida para debaixo do tapete. Quem deseja analisar gráficos Canvas, curvas de evolução patrimonial, projeções de fluxo de caixa, árvores de dependência de projetos e faturas detalhadas encontra tudo a 1 toque de distância, organizado organicamente.
3. **Redução Ativa da Carga Mental (*Reality Buffer*):** O aplicativo absorve a responsabilidade de "ligar os pontos". O usuário não precisa lembrar mentalmente que uma despesa pertencia a um evento ou que uma tarefa dependia de um pagamento.

---

# PARTE 2 — QUATRO PROPOSTAS DE PRODUTO E ARQUITETURA

Apresentamos quatro arquiteturas de experiência genuinamente distintas, explorando modelos mentais, navegações e adaptações responsivas únicas:

```
+----------------------------------------------------------------------------------------------------+
|                                    AS QUATRO PROPOSTAS DA ETAPA 3                                  |
+------------------------------------+----------------------------------+----------------------------+
| PROPOSTA 1: CONSTELLATION          | PROPOSTA 2: HORIZON CHRONO       | PROPOSTA 3: MATRIX DUAL    |
| (Nós Relacionais de Vida)          | (Tempo & Impacto Contínuo)       | (Execução + Cockpit Lado a |
| Navegação: 4 Centros de Gravidade  | Navegação: Linha Crono-Espacial  | Lado em Telas Grandes/DeX) |
+------------------------------------+----------------------------------+----------------------------+
| PROPOSTA 4 (RECOMENDADA): NEXUS SYNAPSE — O SISTEMA DE PROFUNDIDADE PROGRESSIVA ESTRUTURADA       |
| Foco: Home Sintética Concisa + Cartões Conectores Vivos + Hubs Especializados de Alta Densidade     |
+----------------------------------------------------------------------------------------------------+
```

---

## 2.1 PROPOSTA 1: "CONSTELLATION" — Arquitetura de Nós Relacionais

### A. Conceito Central
Toda informação pertence a um **Nó de Vida**. Ao visualizar um item (uma tarefa, um gasto, uma reunião), o usuário sempre tem visível o seu "fio condutor" com o nó pai (ex: Projeto Reforma, Evento Casamento Lucas, Rotina Casa).

### B. Navegação Principal (Mobile e Tablet)
- **4 Hubs:**
  1. `Meu Dia` (Briefing, Hoje e Alertas)
  2. `Linha do Tempo` (Agenda unificada com compromissos e faturas)
  3. `Nós & Iniciativas` (Projetos, Eventos e Metas agrupados como constelações)
  4. `Recursos` (Finanças, Faturas, Orçamentos e Mercado)

### C. Visualização das Conexões
- No detalhe de qualquer transação financeira, surge o badge: `[Vínculo: Evento Viagem SP]` ou `[Vínculo: Tarefa #104]`. Tocar no badge abre a ficha completa do evento com todas as despesas somadas.

---

## 2.2 PROPOSTA 2: "HORIZON CHRONO" — Linha Crono-Espacial Integrada

### A. Conceito Central
O tempo é o eixo soberano. O NEXUS organiza a vida em uma fita temporal infinita e contínua onde tarefas, gastos com vencimento, compromissos e banners de viagens convivem lado a lado na exata ordem cronológica em que acontecem.

### B. Navegação Principal
- **3 Modos de Zoom:**
  1. `Hoje` (A visão de foco cirúrgico)
  2. `Horizonte 14 Dias` (A fita integrada de compromissos, contas a vencer e entregas)
  3. `Panorama Analítico` (Acesso direto aos gráficos Canvas: Donut de Gastos, BarGroupChart, Curva de Metas e Indicadores de Mercado)

### C. Visualização das Conexões
- A fita exibe o compromisso *"Dentista"* às 15h com um chip financeiro acoplado `R$ 250 (Pix)` e um checklist preparatório de 2 subtarefas embutidas.

---

## 2.3 PROPOSTA 3: "MATRIX DUAL" — Arquitetura Especializada de Alta Densidade e Suporte Total a DeX

### A. Conceito Central
Projetada com prioridade nativa para múltiplos perfis: desde o usuário individual até o assistente/secretária que administra a rotina e as finanças de outra pessoa com máxima precisão.

### B. Navegação em Telas Maiores (Tablet Paisagem e Samsung DeX)
- **Estrutura Master-Detail Real:**
  - *Painel Esquerdo (Fixo / Largura 380dp):* Navegação lateral vertical retrátil + Calendário com timeline e filtros rápidos.
  - *Painel Central/Direito (Fluido):* Área de trabalho aberta. Ao clicar em um dia, abre simultaneamente os compromissos, as pendências operacionais e as contas que vencem naquela data.
  - *Painel de Telemetria Lateral Opcional:* Gráficos em tempo real sem sobrepor a agenda.

---

## 2.4 PROPOSTA 4 (RECOMENDAÇÃO AUTORAL): "NEXUS SYNAPSE" — Profundidade Progressiva com Conexões Vivas

Esta proposta foi concebida para atender cirurgicamente a todos os pontos da nova filosofia: **concisa na recepção matinal, mas ilimitadamente rica na exploração de dados.**

### A. Arquitetura da Home (A Recepção Concisa & Conectada)
1. **Barra Superior Nobre:** Wordmark N-1 Kinetik Cut, data corrente, indicador discreto de fuso e alternador suave de perfil/visualização.
2. **Momento Reflexivo:** A Frase do Dia selecionada da **Biblioteca de Frases**, apresentada com tipografia elegante e respiro visual.
3. **O Foco de Hoje (Cartões Vivos Conectados):**
   - Não exibe listas infinitas. Exibe a sequência do dia: próximo compromisso com mapa/custo, tarefas prioritárias e o alerta financeiro relevante (*"Fatura XP fecha em 3 dias: R$ 2.450 / 8 compras"*).
4. **O Hub de Eventos Próximos:**
   - Card cinematográfico com o banner em alta resolução do próximo grande evento (ex: *"Viagem Florianópolis — Em 12 dias"*), exibindo abaixo o total já comprometido no cartão e as tarefas pendentes de bagagem.
5. **Portais de Profundidade:**
   - Botões táteis discretos que levam para a análise profunda dos gráficos sem poluir a Home: `[Ver Saúde Financeira Completa →]` e `[Abrir Linha do Tempo da Semana →]`.

### B. O Fluxo de Profundidade Progressiva em 5 Níveis (Demonstração Prática):
1. **Nível 1 (Superfície - 10s):** Na Home, o usuário visualiza o bloco financeiro resumido: *"Resultado de Novembro: +R$ 1.840 · Orçamento 68% consumido"*.
2. **Nível 2 (Expansão do Módulo - 30s):** Clica no card. Abre a `Central Financeira`: visualiza o gráfico `DonutChart` nativo em Canvas com fatias interativas e o `BarGroupChart` de 6 meses comparando Previsto vs Realizado.
3. **Nível 3 (Filtragem Analítica):** Toca na fatia "Alimentação / Mercado". A lista abaixo se filtra instantaneamente, exibindo todas as despesas daquela categoria.
4. **Nível 4 (O Nó Relacional / Conexão Viva):** Clica na compra *"Mercado Zona Sul - R$ 380,00"*. A tela de detalhe revela:
   - Categoria e forma de pagamento (Cartão XP, Fatura de Dezembro).
   - Tag de vínculo: `[Associado ao Evento: Jantar de Aniversário Júlia]`.
   - Itens comprados e comprovante/anotação.
5. **Nível 5 (Navegação Cruzada):** Com 1 toque na tag, o usuário salta diretamente para a ficha do `Evento: Jantar de Aniversário Júlia`, onde visualiza a lista de convidados, os horários e o orçamento total consolidado do evento.

---

# PARTE 3 — ADAPTAÇÃO PARA TABLETS, PAISAGEM E SAMSUNG DeX

| Plataforma / Modo | Organização do Espaço | Comportamento da Profundidade | Interação Especial |
| :--- | :--- | :--- | :--- |
| **Smartphone Retrato** | Coluna única contínua com abas inferiores (`Hoje`, `Agenda`, `Ações`, `Finanças`, `Mais`). | Navegação por camadas verticais e folhas de detalhe expansíveis (*Sheets*). | Gestos de swipe para concluir e captura universal flutuante. |
| **Tablet Retrato (768dp+)** | Grid adaptativo de 2 colunas proporcionais; cabeçalho enriquecido. | Agenda com 7 dias visíveis simultaneamente sem rolagem cortada. | Toque duplo para expandir cards analíticos. |
| **Tablet Paisagem (1024dp+)** | **Dois Painéis Reais (Split View 40/60):**  <br>• Esquerda: Linha do tempo, lista de projetos ou seletor de faturas.  <br>• Direita: Ficha de detalhe contínua com histórico, subtarefas e gráficos Canvas. | Zero modais intrusivos. O contexto principal nunca se perde. | Navegação em árvore com seleção persistente. |
| **Samsung DeX (Desktop Mode)** | Interface em janela livre, navegação lateral compacta, densidade tipográfica tabular `tnum`. | Três colunas opcionais (Navegação > Lista de Trabalho > Inspeção de Detalhes e Relações). | Atalhos de teclado (`Ctrl+N` para captura, setas para navegar na lista, `Esc` para voltar), clique com botão direito contextual. |

---

# PARTE 4 — PROTÓTIPO VISUAL NAVEGÁVEL

Em cumprimento rigoroso à diretriz de entrega de artefatos visuais concretos e interativos, criamos uma aplicação de prototipagem visual em HTML/CSS/JS estruturada no Design System oficial do NEXUS, permitindo:
- Alternar entre as propostas e modos de tela (Mobile, Tablet Paisagem e Visão de Conexões).
- Interagir com o fluxo de **Profundidade Progressiva** (da Home sintética até a ficha relacional de despesas e eventos).
- Experimentar a **Wordmark N-1 Kinetik Cut**, a **Biblioteca de Frases**, os cartões com imagens fotográficas e os gráficos interativos em Canvas escuro/grafite.

*(O protótipo visual completo e interativo foi salvo e publicado no DataStore e exibido no Canvas para inspeção direta).*

---

# PARTE 5 — RECOMENDAÇÃO TÉCNICA E PRÓXIMOS PASSOS

Recomendamos a adoção da **PROPOSTA 4 (NEXUS SYNAPSE)** pelas seguintes razões:
1. **Respeito Absoluto à Cognição Humana:** Não obriga ninguém a ser analista de dados às 7h da manhã, mas entrega o poder de um banco relacional maduro a quem precisa dele.
2. **Atendimento ao Perfil de Administração Dupla:** A visualização em painéis paralelos resolve tanto a vida pessoal quanto o caso de uso da secretária que administra a rotina de terceiros com despesas, dependências e horários sincronizados.
3. **Preservação Arquitetural Sólida:** Compatibilidade de 100% com o schema Room v6 existente, `FinanceRules` sem duplicidade, motor `AgendaEngine` e o novo módulo de eventos com banner local/online.
