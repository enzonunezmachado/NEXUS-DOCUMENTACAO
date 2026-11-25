# 00 — Visão, escopo e governança da reformulação

**Estado geral:** EM ANÁLISE  
**Origem:** conversas de 24–25/11/2026 e consolidações posteriores.  
**Natureza:** direção conceitual e requisitos de experiência; não é especificação técnica executável.

## 1. Intenção central

O NEXUS deve evoluir de um sistema funcional para uma experiência de produto mais refinada, agradável, fluida, expressiva e confiável. A reformulação é ampla: repensa como o usuário percebe, encontra, compreende e utiliza as informações em todo o aplicativo.

O problema não é simplesmente “deixar as telas bonitas”. A interface precisa expressar melhor a natureza de cada informação, a importância de cada ação e as relações entre registros. Não queremos uma planilha com uma capa bonita, nem uma interface minimalista que esconda a profundidade do sistema.

A reconstrução é principalmente da experiência e da apresentação. Não implica reescrever o aplicativo, substituir tecnologias ou alterar automaticamente o modelo de dados. Mudanças no domínio, persistência, regras de negócio ou arquitetura de software exigem investigação e aprovação próprias.

## 2. Personalidade do produto

A referência do usuário é incorporar aproximadamente **15% da essência do Luma**. É uma metáfora de direção, não uma métrica nem uma autorização para copiar sua interface.

O que interessa nessa inspiração:
- Conteúdo com protagonismo, não apenas caixas e listas.
- Capas e imagens quando ajudam a reconhecer ou compreender algo.
- Cards bem compostos, com hierarquia, proporção e cantos coerentes.
- Uso intencional de cor, superfícies e degradês.
- Sensação de produto vivo, cuidado e convidativo.
- Microinterações e transições que dão continuidade e feedback.

O NEXUS mantém personalidade própria:
- Base sóbria em preto, branco e grafite.
- Sofisticação sem frieza; expressividade sem ruído.
- Cores contextuais e semânticas usadas com propósito.
- Ambientes com personalidade adequada à sua função, dentro de um sistema visual coerente.
- Identidade Android própria; não imitar superficialmente padrões de iOS.

A referência pode aparecer em intensidades diferentes conforme o domínio. Mercado pode explorar visualizações e degradês mais expressivos; Eventos/Planejamento pode dar protagonismo a capas e contexto; Tarefas precisa preservar densidade e agilidade. Isso não significa criar um tema independente para cada aba.

## 3. Princípios de experiência

1. **O conteúdo deve conduzir a composição.** A tela se adapta à quantidade, natureza e relevância dos registros.
2. **Densidade é contextual.** Tarefas podem ser compactas; eventos e projetos podem merecer maior expressão. Nem tudo vira card grande.
3. **Poucos dados também devem gerar uma boa tela.** Não deixar itens minúsculos perdidos em grandes vazios, nem inventar conteúdo para preencher espaço.
4. **Cada entidade tem identidade própria.** Evento, projeto, objetivo, tarefa e registro financeiro não são a mesma ficha com campos trocados.
5. **Profundidade progressiva.** A leitura inicial é simples; detalhes, históricos, filtros e análises continuam acessíveis.
6. **Relações aparecem quando ajudam.** Não despejar a árvore inteira de vínculos em todos os contextos.
7. **Interações são inequívocas.** Concluir, consultar, expandir, editar, navegar e excluir são ações diferentes.
8. **Consistência não é uniformidade artificial.** Componentes compartilham princípios, mas sua composição respeita o propósito.
9. **Acessibilidade faz parte do design.** Não depender apenas de cor; considerar contraste, daltonismo, tamanho, áreas de toque e movimento reduzido.
10. **Integridade antes do efeito visual.** Nenhuma melhoria pode quebrar dados, cálculos, histórico, permissões, regras ou navegação.
11. **Adaptação real.** Celular e tablet, retrato e paisagem, devem reorganizar conteúdo conforme o contexto, não apenas esticar o layout.

## 4. Escopo da iniciativa

A investigação pode abranger:
- Arquitetura da informação e organização das áreas.
- Modelo mental e hierarquia das entidades.
- Home/Hoje, Agenda, Tarefas, Planejamento, Dinheiro, Mercado e demais áreas existentes.
- Linguagem visual, tipografia, espaçamento, superfícies, bordas, raios, cores e símbolos.
- Cards, linhas, fichas especializadas e estados vazios.
- Relações contextuais, consulta, edição, conclusão e navegação.
- Adaptação para telas grandes.
- Microinterações, movimento, desempenho e acessibilidade.

A iniciativa não autoriza por si só:
- Reescrever o aplicativo do zero.
- Trocar Kotlin, Jetpack Compose, Room ou a arquitetura vigente.
- Alterar modelo de dados, persistência, cálculos ou regras financeiras sem análise.
- Remover capacidades existentes para alcançar minimalismo.
- Adotar a proposta de navegação do Claude integralmente.
- Implementar um redesign amplo em um único lote sem decomposição e validação.

## 5. Separação entre experiência e arquitetura técnica

É necessário distinguir três níveis:

- **Arquitetura da informação:** como o usuário encontra e compreende áreas, registros e funções.
- **Arquitetura de interação:** como consulta, expansão, edição, conclusão e navegação se comportam.
- **Arquitetura de software e dados:** como o aplicativo implementa, armazena e relaciona as informações.

Uma mudança na primeira ou segunda camada pode exigir apenas apresentação; também pode revelar a necessidade de mudanças técnicas. Isso deve ser demonstrado, não presumido.

## 6. Fontes de verdade e governança

- O código do repositório ativo `PROJETO-NEXUS-` determina o comportamento implementado.
- A documentação técnica vigente em `PROJETO-NEXUS-/docs/` registra contratos e requisitos técnicos.
- `GOOGLE_IA/` é espelho técnico, não fonte independente.
- Esta pasta `CHATGPT/ATUALIZACAO/` registra intenção, hipóteses, critérios e evolução do pensamento.

A proposta Claude de 24/11/2026 permanece uma contribuição relevante, mas não é a arquitetura escolhida. A visão atual deve ser confrontada com o app real e com as regras existentes.

## 7. Estados e aprovações

Usar os estados documentais vigentes: CAPTURADA, EM PESQUISA, EM ANÁLISE, PROPOSTA, APROVADA, PLANEJADA, EM IMPLEMENTAÇÃO, IMPLEMENTADA, PAUSADA, REJEITADA, DESCARTADA e SUBSTITUÍDA.

**Estado desta iniciativa: EM ANÁLISE.** As direções consolidadas nesta pasta representam entendimento atual do que se busca, mas não equivalem à aprovação de uma arquitetura final, de um sistema de cores, de componentes específicos ou de implementação.

Uma proposta aprovada estrategicamente ainda precisa de especificação técnica, planejamento, critérios de aceite e autorização de implementação.
