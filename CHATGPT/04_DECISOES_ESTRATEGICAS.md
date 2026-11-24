# Decisões estratégicas do NEXUS

**Tipo:** registro de decisões de produto e direção  
**Status:** consolidação inicial

Este arquivo registra decisões estratégicas discutidas com o usuário. Não substitui os ADRs técnicos mantidos em GOOGLE_IA.

## DEC-STR-001 — NEXUS como centro de comando, não apenas gerenciador de tarefas
- **Status:** Consolidada
- **Decisão:** orientar o produto como sistema pessoal e profissional integrado, voltado a reduzir carga cognitiva e oferecer tranquilidade operacional.
- **Contexto:** tarefas isoladas não resolvem a necessidade de coordenar compromissos, eventos, finanças, trabalho e responsabilidades.
- **Consequência:** módulos devem compartilhar contexto sem perder suas identidades nem duplicar registros.

## DEC-STR-002 — Gratuidade do uso essencial
- **Status:** Diretriz estratégica consolidada
- **Decisão:** o núcleo do aplicativo deve ser gratuito e não depender de APIs pagas, assinaturas ou serviços obrigatórios.
- **Contexto:** preocupação explícita com custo de criação e utilização do NEXUS.
- **Consequência:** avaliar dependências externas pelo custo, limites, disponibilidade e possibilidade de fallback. Não prometer gratuidade irrestrita de serviços de terceiros.

## DEC-STR-003 — Autonomia dos dados e funcionamento local-first/offline-first
- **Status:** Diretriz estratégica vigente e revisável; não é proibição permanente.
- **Decisão atual:** priorizar dados locais e funcionamento offline-first, sem conta, backend proprietário ou sincronização em nuvem obrigatórios para o uso essencial.
- **Alcance:** integrações externas opcionais podem utilizar rede quando agregarem valor e forem avaliadas; o módulo Mercado já utiliza provedores externos com cache local.
- **Regra de revisão:** propostas de sincronização, backend, IA remota ou integrações financeiras não alteram esta diretriz até aprovação explícita e atualização do ADR-003/documentos técnicos afetados.
- **Consequência:** backup e restauração são capacidades essenciais; serviços externos devem ter custos, limites, privacidade, segurança e alternativas avaliados.

## DEC-STR-004 — Separação entre intenção estratégica e realidade técnica
- **Status:** Aprovada como regra documental
- **Decisão:** manter a memória estratégica em CHATGPT e a documentação técnica em GOOGLE_IA.
- **Consequência:** uma ideia aprovada não vira automaticamente funcionalidade implementada; o status técnico depende de confirmação no código/documentação técnica.

## DEC-STR-005 — Evolução incremental e preservação
- **Status:** Diretriz consolidada
- **Decisão:** trabalhar em lotes delimitados, evitando reescritas desnecessárias e protegendo dados e funcionalidades existentes.
- **Consequência:** cada lote deve avaliar impacto, atualizar documentação pertinente e registrar testes reais.

## DEC-STR-006 — Memória duradoura no GitHub
- **Status:** Aprovada
- **Data aproximada:** 20/11/2026
- **Decisão:** manter um repositório documental separado do repositório ativo de desenvolvimento, editável e utilizável entre conversas.
- **Consequência:** README orientador, pastas GOOGLE_IA e CHATGPT e sincronização periódica.

## DEC-STR-007 — Não transformar ideias em requisitos automaticamente
- **Status:** Aprovada como regra de governança
- **Decisão:** preservar brainstorms, propostas, decisões, itens enviados e implementações como estados distintos.
- **Consequência:** manter histórico inclusive de ideias pausadas, rejeitadas ou substituídas.

## Decisões ainda não confirmadas

- Arquitetura de um assistente de IA integrado ao aplicativo.
- Integrações com serviços Google e Android além das capacidades já presentes.
- Escopo final de personalização da Home.
- Recorrência de compromissos e eventos.
- Estratégia definitiva de backup ZIP e mídias.

Não registrar essas questões como decisões aprovadas sem nova confirmação explícita.
