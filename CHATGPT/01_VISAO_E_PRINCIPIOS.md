# Visão e princípios estratégicos do NEXUS

**Tipo:** memória estratégica vigente, sujeita a evolução  
**Origem:** conversas e consolidações anteriores  
**Status:** visão consolidada; detalhes de produto podem evoluir

## 1. O que é o NEXUS

**Leitura de autoridade:** esta visão é estratégica. Para interpretar estados, propostas e conflitos, consulte [`00_ORIENTACAO_DE_LEITURA.md`](./00_ORIENTACAO_DE_LEITURA.md). Para a realidade implementada, prevalecem o código e os documentos técnicos vigentes.

NEXUS é concebido como um centro de comando pessoal e profissional. Não é apenas um gerenciador de tarefas, calendário ou aplicativo financeiro. Sua ambição é reunir informações e responsabilidades que normalmente ficam espalhadas entre ferramentas, conversas e lembranças, oferecendo uma visão coerente do que está acontecendo, do que vem a seguir e do que exige atenção.

O valor central é reduzir a carga mental de lembrar, acompanhar e coordenar responsabilidades. O sistema deve oferecer segurança e tranquilidade operacional para que a pessoa possa agir, criar e viver sem precisar manter tudo na cabeça.

## 2. Problema que pretende resolver

A vida pessoal e profissional envolve tarefas, compromissos, eventos, família, trabalho, projetos, obrigações financeiras e rotinas. Quando essas informações ficam fragmentadas, a pessoa precisa reconstruir o contexto repetidamente, lembrar prazos e conferir diferentes lugares.

O NEXUS busca centralizar esse contexto sem apagar as diferenças entre os tipos de informação.

## 3. Princípios estratégicos consolidados

- **Reduzir carga cognitiva:** o aplicativo deve retirar da mente do usuário o trabalho de lembrar e acompanhar.
- **Segurança operacional:** transmitir confiança de que informações, prazos e responsabilidades estão organizados.
- **Visão integrada:** módulos podem compartilhar contexto, sem criar registros duplicados.
- **Fonte única de verdade:** um mesmo fato não deve ser cadastrado várias vezes apenas para aparecer em telas diferentes.
- **Local-first / offline-first:** funções essenciais devem continuar úteis sem conexão. A arquitetura técnica vigente está registrada no ADR-003; propostas de sincronização ou serviços externos não a alteram sem aprovação formal.
- **Gratuidade como princípio de produto:** a criação e o uso essencial devem ser gratuitos; não depender de APIs pagas, assinatura ou infraestrutura obrigatória.
- **Autonomia e controle dos dados:** priorizar armazenamento local, sem conta obrigatória, backend ou Firebase obrigatório, com backup/exportação sob controle do usuário.
- **Clareza acima de quantidade:** apresentar muita informação com pouca confusão.
- **Profundidade acessível:** a experiência deve ser concisa e agradável sem esconder complexidade útil. Gráficos, indicadores, históricos e relações entre registros devem estar disponíveis quando necessários, sem despejar tudo na tela inicial.
- **Clareza das conexões:** não basta reunir módulos; a experiência deve tornar perceptível como tempo, atividades, eventos, projetos e dinheiro se relacionam.
- **Adaptação ao espaço:** tablets em retrato/paisagem e ambientes como Samsung DeX devem aproveitar espaço adicional para mostrar mais contexto e permitir operações simultâneas, sem simplesmente esticar a interface.
- **Evolução incremental:** desenvolver em lotes, preservando dados e funcionalidades.
- **Honestidade informacional:** não inventar registros, cotações, status ou resultados para preencher lacunas.

## 4. Experiência desejada

O usuário deve conseguir entender rapidamente:
- O que está acontecendo agora.
- O que vem a seguir.
- O que está atrasado ou exige atenção.
- O que foi concluído.
- Quais compromissos e obrigações precisam de acompanhamento.

A Home deve funcionar como centro de comando, não como uma lista interminável nem como substituta dos módulos.

## 5. Limites entre visão e implementação

Os princípios acima representam a direção estratégica registrada. Eles não significam que todos os recursos necessários já estejam implementados ou validados. Para saber o estado real do aplicativo, consultar `GOOGLE_IA/` e o código ativo.


## 6. Contexto de origem e uso

Nas conversas de 15/11/2026, o NEXUS também foi descrito como um projeto pessoal, funcional e instalável, priorizando o uso diário do próprio usuário, não a comercialização como objetivo inicial. Foi considerada a possibilidade de disponibilizar o código como open source, permitindo modificações e até comercialização por terceiros; isso foi discutido como possibilidade, não como decisão de licença formal já definida.

Cada instalação deve começar vazia e manter seus próprios dados locais isolados. A experiência e a arquitetura não devem pressupor uma conta compartilhada ou uma base central que misture dados de diferentes instalações.

## 7. Identidade visual de origem

A direção visual inicial registrada em 15/11/2026 foi uma identidade escura, com base preta, tipografia branca e símbolo/logo “N”. Essa direção deve ser lida junto ao Design System técnico vigente, que pode ter evoluído em tokens, temas e componentes.
