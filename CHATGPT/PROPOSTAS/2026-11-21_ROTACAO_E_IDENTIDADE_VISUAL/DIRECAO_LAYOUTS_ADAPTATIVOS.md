# Direção em análise — layouts adaptativos, Agenda e rotação

**Registro:** 22/11/2026  
**Tipo:** observação de UX e proposta de exploração visual/técnica  
**Estado:** capturada / em análise  
**Origem:** feedback posterior à avaliação do tema Grafite Suave e revisão da Agenda  
**Relação:** IDEA-006 e BUG-001

## 1. Objetivo

A interface do NEXUS deve aproveitar o espaço disponível de maneira intencional. Em celulares, tablets e telas maiores, a experiência não deve ser simplesmente esticada: painéis e componentes podem ser reorganizados conforme largura, altura e orientação, mantendo continuidade de contexto.

Esta direção reúne três necessidades relacionadas, mas distintas: proporção vertical dos cartões semanais; layouts adaptativos para tablets deitados e telas tipo monitor; preservação de tela e estado durante rotação, relacionada ao BUG-001.

## 2. Agenda semanal em celular

O usuário solicitou que os cartões/dias da visão semanal tenham proporção vertical coerente com a tela disponível.

- Considerar a altura útil real da janela, descontando barras do sistema e áreas ocupadas pela interface.
- Distribuir a altura de forma equilibrada, evitando cartões comprimidos ou excessivamente altos.
- Adaptar a composição à orientação retrato/paisagem e a diferentes proporções de tela.
- Manter textos, indicadores e alvos de toque legíveis.
- Não depender de uma altura fixa válida apenas para um modelo de aparelho.

A proporção exata deve ser demonstrada em protótipo e validada em diferentes tamanhos antes de virar especificação de implementação.

## 3. Agenda em tablet deitado — duas áreas

Exemplo desejado: apresentar os dias/calendário da Agenda em uma área do tablet e os itens do dia selecionado na outra. Ao selecionar outro dia, atualizar o painel de itens sem perder o contexto do calendário.

A composição pode usar duas áreas lado a lado, mas não se deve fixar automaticamente uma divisão de 50/50. A proporção deve considerar densidade, legibilidade e quantidade de informação.

Avaliar navegação semanal, seleção de dia, painel de itens, rolagem, estados vazios, dias com muitos itens, conteúdos longos e continuidade da seleção ao alternar orientação ou redimensionar a janela.

## 4. Telas grandes e formato tipo monitor

Estudar uma experiência expandida para janelas largas: painéis simultâneos quando reduzirem navegação desnecessária; colunas e áreas laterais para detalhes; hierarquia e densidade adequadas; mesmos dados e regras de negócio entre layouts. Avaliar teclado e foco se o ambiente oferecer essas modalidades.

A referência a monitores é uma direção de adaptação visual, não uma decisão de transformar imediatamente o NEXUS em aplicativo desktop ou garantir suporte a qualquer janela livre.

## 5. Rotação e preservação de estado — relação com BUG-001

A adaptação visual não resolve, por si só, a perda de estado relatada ao girar o dispositivo.

O BUG-001 continua cobrindo a preservação da tela/rota, aba e contexto de navegação, conteúdo digitado, seleções, filtros, data selecionada, progresso de criação/edição e menus/diálogos relevantes, quando aplicável.

A implementação deve tratar a recriação da Activity e distinguir rotação de encerramento do processo. A análise técnica já reportada pelo AI Studio deve ser consultada antes de definir arquitetura ou escopo.

A interface deve adaptar sua composição à nova orientação sem descartar o contexto do usuário. São critérios complementares: continuidade funcional (não perder o que estava fazendo) e continuidade visual (reorganizar o conteúdo para a nova dimensão).

## 6. Critérios gerais de avaliação

- O layout se adapta às dimensões reais da janela, não apenas ao modelo do dispositivo.
- A Agenda mantém seleção e contexto ao alternar retrato/paisagem.
- Em tablet deitado, calendário e itens do dia podem permanecer visíveis simultaneamente.
- Cartões semanais aproveitam a altura disponível sem comprometer legibilidade.
- Telas maiores ganham organização mais eficiente, não apenas espaços vazios.
- A interface mantém acessibilidade, alvos de toque e hierarquia.
- Preferências não são perdidas quando a composição muda.

## 7. Investigação técnica futura

Quando houver aprovação para implementação, avaliar Compose adaptive layouts e window size classes; dimensões compactas, médias e expandidas; orientação e mudanças de tamanho de janela; estado compartilhado entre painel de calendário e painel de itens; ViewModel/SavedStateHandle e mecanismos pertinentes ao BUG-001; testes em diferentes proporções e orientações.

Não presumir breakpoints, APIs, estrutura de navegação ou arquitetura antes de inspecionar o código vigente.

## 8. Limites e estado

- Esta direção é capturada/em análise, não requisito aprovado para implementação.
- O exemplo de Agenda em duas áreas é a referência principal, mas a composição final deve ser avaliada.
- Não há autorização para alterar código Android, navegação ou tema de produção nesta etapa.
- Não marcar BUG-001 como corrigido por registrar esta direção.
- Não definir breakpoints, dimensões ou proporções numéricas sem prototipagem e inspeção técnica.

## 9. Relação com a documentação estratégica

Esta direção detalha a ideia existente **IDEA-006 — Layouts adaptativos para celular, tablet e telas maiores**. O BUG-001 permanece no documento de problemas/correções porque descreve a perda funcional de estado durante rotação. O catálogo central e o quadro de pendências apontam para este documento, sem criar uma ideia duplicada.