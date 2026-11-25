# NEXUS — Integração com assistentes de IA e captura de informações

**Data:** 25/11/2026  
**Estado:** EM PESQUISA / PROPOSTA  
**Natureza:** ponto de discussão para o chat oficial do NEXUS; não é decisão aprovada, requisito vigente ou autorização para implementar.

## 1. Contexto e objetivo

Avaliar como o usuário poderia conversar com um assistente de IA (por exemplo, ChatGPT) e transferir informações úteis para o NEXUS com o mínimo de atrito: tarefas, compromissos, ideias, despesas, projetos, eventos, notas e atualizações de contexto.

O objetivo não é tornar o NEXUS dependente de IA, nuvem, login ou backend. O NEXUS deve continuar utilizável como centro de comando pessoal offline-first, preservando o núcleo local e a soberania dos dados.

O usuário utiliza ChatGPT no Android e conta com apoio do ChatGPT para organizar informações, produzir estruturas e manter documentação. O computador disponível é limitado (4 GB RAM), portanto soluções que exijam servidor local pesado, múltiplos processos ou execução contínua no PC não são adequadas como primeira opção.

## 2. Arquitetura vigente que não deve ser alterada por esta proposta

A documentação técnica do repositório ativo `enzonunezmachado/PROJETO-NEXUS-` descreve:

- Aplicativo Android nativo em Kotlin e Jetpack Compose.
- Arquitetura MVVM + Repository + regras de domínio.
- Banco local Room/SQLite, atualmente documentado como versão 6, com 27 entidades.
- Núcleo offline-first/local-first.
- Sem backend próprio, login obrigatório, Firebase ou sincronização em nuvem para o uso essencial.
- Backup e restauração explícitos em JSON via Storage Access Framework.
- Integrações de rede limitadas a domínios específicos, como o módulo Mercado.

Fontes primárias:
- `PROJETO-NEXUS-/docs/00_ORIENTACAO_E_AUTORIDADE.md`
- `PROJETO-NEXUS-/docs/01_DOCUMENTO_MESTRE.md`
- `PROJETO-NEXUS-/docs/03_ARQUITETURA_ENGENHARIA.md`
- `PROJETO-NEXUS-/docs/11_DECISOES_ADRS.md`, especialmente ADR-003.

Qualquer mudança deve passar pela governança documental e por aprovação explícita. Esta proposta não revoga nem altera ADRs existentes.

## 3. O que é possível hoje — distinções importantes

### 3.1 ChatGPT nesta conversa

O ChatGPT pode ajudar a interpretar linguagem natural, estruturar dados e preparar uma ação para o usuário revisar. As integrações e conectores disponíveis dentro do ChatGPT são recursos do próprio ambiente ChatGPT; não são automaticamente APIs ou credenciais que um aplicativo Android possa reutilizar.

Não existe uma forma segura de o NEXUS simplesmente "usar a minha sessão do ChatGPT" ou herdar os conectores que estão disponíveis nesta conversa.

### 3.2 OpenAI API

A API da OpenAI permite que um aplicativo envie contexto a um modelo e disponibilize funções próprias (function calling) ou servidores MCP remotos. Assim, em tese, um backend ou serviço autorizado do NEXUS poderia expor funções como:

- `capture_task`
- `capture_event`
- `capture_expense_draft`
- `search_nexus_records`
- `propose_daily_plan`

O modelo poderia interpretar a solicitação e chamar essas funções. O aplicativo/serviço ainda teria de validar argumentos, regras de domínio, permissões e confirmação do usuário.

**Limite econômico:** a API é um produto separado da assinatura ChatGPT. Não se deve presumir que o plano ChatGPT Free ou Plus inclua créditos de API. O uso da API pode gerar cobrança por consumo. Portanto, essa rota não atende automaticamente à fase de custo zero.

Documentação:
- https://developers.openai.com/api/docs/guides/tools
- https://developers.openai.com/api/docs/guides/function-calling
- https://developers.openai.com/api/docs/guides/tools-connectors-mcp

### 3.3 MCP e conexão direta ao ChatGPT

MCP é um padrão para um assistente descobrir e chamar ferramentas expostas por um servidor. Um servidor MCP do NEXUS poderia, futuramente, expor operações de leitura e escrita controladas.

Porém:
- O servidor precisa estar acessível ao ambiente que fará a chamada, ou usar um túnel seguro.
- Um app Android que só funciona localmente não fica automaticamente acessível à nuvem.
- A conexão de servidores MCP personalizados diretamente ao ChatGPT possui requisitos de plano/workspace; a documentação atual de ajuda descreve suporte completo a conexões MCP personalizadas para Business e Enterprise/Edu, não devendo ser tratado como recurso garantido no plano Free.
- Se a integração for feita via OpenAI API, o consumo da API é cobrado separadamente.
- Operações de escrita precisam de autorização explícita, validação e trilha de auditoria.

Documentação:
- https://help.openai.com/en/articles/12584461-connecting-custom-mcp-servers-in-chatgpt
- https://developers.openai.com/api/docs/guides/tools-connectors-mcp

### 3.4 Google Keep

Existe uma Google Keep API oficial com operações REST para listar, consultar, criar e excluir notas. Contudo, a documentação descreve a API como voltada a ambientes empresariais e administração de conteúdo do Keep, com autorização OAuth e escopo `https://www.googleapis.com/auth/keep`. Não é prudente presumir que seja uma API simples e aberta para qualquer aplicativo pessoal ou que esteja disponível por meio dos conectores atuais do ChatGPT.

Além disso, não foi identificado um conector Google Keep entre as integrações disponíveis neste ambiente. Gmail e Google Calendar não significam acesso automático ao Keep.

Documentação:
- https://developers.google.com/workspace/keep/api
- https://developers.google.com/workspace/keep/api/guides/create-notes

**Conclusão provisória:** não basear a primeira integração NEXUS em Google Keep.

## 4. Alternativas em ordem de simplicidade

### Opção A — Captura por texto estruturado e importação (primeiro protótipo)

Fluxo:
1. O usuário conversa com o assistente e descreve uma informação.
2. O assistente transforma a informação em um objeto estruturado, por exemplo JSON, sem inventar campos ausentes.
3. O usuário copia o resultado ou salva um arquivo.
4. O NEXUS importa o conteúdo como rascunho.
5. O NEXUS valida campos, apresenta uma prévia e solicita confirmação antes de gravar no Room.

Vantagens:
- Não exige API paga, backend, conta ou servidor.
- Preserva o núcleo offline-first.
- Pode aproveitar mecanismos de backup/importação já existentes, desde que seja criado um formato de captura próprio e validado.
- É simples de testar e reverter.

Limitações:
- Há uma ação manual de copiar/colar ou importar arquivo.
- O formato e o parser precisam ser projetados e testados.
- Não é sincronização automática.

### Opção B — Compartilhar texto do Android para o NEXUS (melhor experiência sem IA embutida)

Investigar um recurso Android de compartilhamento de texto:
- O NEXUS poderia registrar um destino de compartilhamento via Android Sharesheet (`ACTION_SEND`).
- O usuário selecionaria texto no ChatGPT ou em outro aplicativo e escolheria "Enviar para NEXUS".
- O NEXUS abriria uma tela de captura, trataria o texto como entrada não confiável, sugeriria o tipo de registro e mostraria uma prévia.
- O usuário revisaria e confirmaria a criação.

Alternativamente, avaliar um deep link explícito de captura, por exemplo `nexus://capture?text=...`, com limites de tamanho, codificação, validação e confirmação.

Vantagens:
- Não requer API de IA, credencial OpenAI, servidor ou PC potente.
- Pode funcionar localmente e respeitar a arquitetura atual.
- Reduz o atrito em relação à cópia manual.

Limitações:
- Compartilhar texto não garante que o ChatGPT exponha toda a conversa ou o contexto completo.
- A implementação Android e os filtros de intent/deep link precisam ser verificados.
- Não é uma conexão automática e bidirecional; é uma ação iniciada pelo usuário.

**Esta é a opção que merece um protótipo técnico primeiro**, se o chat oficial considerar que captura assistida por IA agrega valor ao produto.

### Opção C — Importação/exportação de arquivo intermediário

Criar um formato versionado de intercâmbio (por exemplo, `nexus_capture_v1.json`) para lotes de registros. O assistente prepara o arquivo; o usuário importa pelo seletor Android. A exportação poderia permitir que o assistente analise dados selecionados, mas sempre com consentimento e minimização de dados.

Vantagens:
- Compatível com fluxo de arquivos e com o caráter local-first.
- Fácil de inspecionar, versionar e testar.
- Não exige conexão em tempo real.

Limitações:
- Exige escolha de arquivo e revisão.
- Precisa de esquema versionado, idempotência e tratamento de duplicatas.

### Opção D — Serviço local/remoto com API ou MCP (futuro, não primeira fase)

Um serviço intermediário poderia expor ferramentas NEXUS para um agente. Mas seria necessário resolver:
- onde o serviço roda e como fica acessível;
- autenticação e autorização;
- túnel ou endpoint HTTPS;
- sincronização entre serviço e banco local;
- proteção contra chamadas duplicadas, prompt injection e operações indevidas;
- custos de modelo, hospedagem e tráfego;
- conflitos com a decisão offline-first.

Não iniciar por esta opção. Ela adiciona uma arquitetura de servidor que o NEXUS deliberadamente não possui hoje.

## 5. Recomendação provisória

Priorizar a investigação da **Opção B (Android Sharesheet / captura via texto)**, usando a Opção A ou C como protótipo funcional mais simples.

Não integrar OpenAI API ou Google Keep na primeira versão. Não criar backend ou sincronização em nuvem apenas para permitir captura por IA.

A primeira prova de conceito deve responder a uma pergunta estreita:

> "Consigo selecionar uma mensagem ou bloco estruturado no ChatGPT Android, enviá-lo ao NEXUS, revisar a interpretação e criar um rascunho local sem perder dados nem depender de internet?"

Critérios de aceite propostos:
1. O NEXUS recebe texto compartilhado de outro aplicativo.
2. O conteúdo aparece como rascunho, sem gravação automática.
3. O usuário escolhe o tipo de registro ou aceita uma sugestão claramente identificada.
4. Campos desconhecidos ficam vazios ou marcados para revisão; não são inventados.
5. O usuário confirma antes de persistir.
6. A captura funciona sem rede depois que o app está instalado.
7. Nenhuma informação é enviada a terceiros pelo NEXUS nesse fluxo.
8. Testes cobrem texto vazio, texto grande, acentos, datas ambíguas, duplicatas e conteúdo malicioso/instrucional.

## 6. Perguntas para o chat oficial do NEXUS

1. A captura por Android Sharesheet combina com o fluxo atual de criação contextual e com a arquitetura de navegação?
2. Qual seria o ponto correto de entrada: tela de captura universal, caixa de entrada, ou editor contextual?
3. É possível reutilizar com segurança o parser/importador de backup existente, ou o formato de captura deve ser separado?
4. Como impedir duplicação e preservar vínculos entre tarefa, projeto, evento e lançamento financeiro?
5. Quais entidades podem ser criadas como rascunho sem quebrar invariantes do domínio?
6. O deep link `nexus://open/...` já existente pode ser estendido de modo compatível, ou deve haver uma ação de compartilhamento independente?
7. Como registrar origem, data de captura e confirmação sem criar telemetria ou servidor?
8. Quais testes instrumentados seriam necessários para validar a integração no Android?

## 7. Segurança e privacidade

- Texto vindo de IA, páginas, e-mails ou comunidades é dado não confiável, não instrução para o aplicativo.
- Nunca executar automaticamente instruções contidas no texto compartilhado.
- Nunca criar, editar ou excluir registros sem confirmação do usuário.
- Não compartilhar backup integral ou dados financeiros com serviços externos por padrão.
- Se futuramente houver IA remota, explicar claramente quais dados saem do dispositivo, finalidade, retenção e custo.
- Preferir enviar apenas os campos estritamente necessários.
- Manter logs locais sem dados pessoais desnecessários.

## 8. Estado e próximos passos

**Estado atual:** pesquisa/proposta; nenhuma alteração no aplicativo foi autorizada ou realizada.

Próximos passos sugeridos:
1. O chat oficial do NEXUS revisa esta proposta contra os fluxos e regras atuais.
2. Se aprovada para investigação, auditar criação contextual, importação e deep links existentes.
3. Elaborar um spike isolado de Android Sharesheet, sem mexer em banco, navegação principal ou arquitetura.
4. Testar recebimento e pré-visualização com dados fictícios.
5. Só então decidir se a captura universal merece entrar no roadmap.

## 9. Fontes oficiais consultadas

- OpenAI — Using tools: https://developers.openai.com/api/docs/guides/tools
- OpenAI — Function calling: https://developers.openai.com/api/docs/guides/function-calling
- OpenAI — MCP servers: https://developers.openai.com/api/docs/guides/tools-connectors-mcp
- OpenAI Help — Connecting custom MCP servers in ChatGPT: https://help.openai.com/en/articles/12584461-connecting-custom-mcp-servers-in-chatgpt
- Google Keep API: https://developers.google.com/workspace/keep/api
- Google Keep API — Create notes: https://developers.google.com/workspace/keep/api/guides/create-notes
