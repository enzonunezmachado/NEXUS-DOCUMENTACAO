# Proposta — organização documental Diátaxis e exploração visual com Obsidian

**Data de captura:** 26/11/2026  
**Estado:** CAPTURADA / EM PESQUISA — NÃO APROVADA PARA IMPLEMENTAÇÃO  
**Escopo:** documentação e exploração visual do conhecimento NEXUS. Não altera o aplicativo Android nem o modelo de dados.

## 1. Origem e intenção

O usuário quer estudar práticas profissionais de documentação técnica para projetos conduzidos com IA e avaliar como melhorar a estrutura Markdown do NEXUS. Também demonstrou interesse em visualizar graficamente as conexões entre registros, módulos e conceitos do NEXUS, considerando Obsidian.

A proposta deve preservar a preferência por controle humano, leitura progressiva e separação clara entre ideia, proposta, decisão, especificação e implementação.

## 2. Diátaxis — hipótese para melhorar a documentação

Diátaxis organiza documentação em quatro necessidades:

1. **Tutoriais:** ensinar uma pessoa a concluir uma primeira tarefa.
2. **Guias práticos (how-to):** orientar uma tarefa específica.
3. **Referência:** descrever contratos, campos, regras, interfaces, comandos e estruturas.
4. **Explicações:** registrar conceitos, contexto e justificativas.

Referência: https://diataxis.fr/

### Aplicação potencial ao NEXUS
- Tutoriais: configurar o projeto, executar testes, preparar ambiente e realizar backup de desenvolvimento.
- Guias práticos: adicionar um módulo, alterar uma entidade, criar uma migração Room, atualizar um widget, investigar falhas.
- Referência: entidades, DAOs, contratos JSON, enums, rotas, ADRs, tabelas, regras de negócio e APIs.
- Explicações: offline-first, decisões de persistência, sincronização, integração entre módulos, trade-offs de UX e arquitetura.

### Limite
A documentação atual já possui separação por especificação, arquitetura, modelo de dados, fluxos, estado, qualidade, guia de desenvolvimento, roadmap, ADRs, rastreabilidade e governança. Portanto, Diátaxis deve ser aplicado como classificação e melhoria incremental, não como motivo para renomear ou reorganizar todos os arquivos numerados. Primeiro identificar lacunas reais de tutoriais/how-to e ambiguidades de finalidade.

## 3. Obsidian — hipótese de exploração visual

Referências:
- Graph View: https://obsidian.md/help/plugins/graph
- Bases: https://obsidian.md/help/bases

Obsidian trabalha sobre arquivos Markdown locais. O Graph View visualiza notas como nós e links internos como conexões. Bases permite criar visualizações tabulares, cartões, listas e outras vistas a partir das propriedades dos arquivos Markdown.

### Benefício potencial
- Explorar visualmente relações entre conceitos, módulos, documentos, decisões e requisitos.
- Identificar documentos centrais, conexões ausentes e áreas isoladas.
- Navegar pelo conhecimento do projeto por links e propriedades.
- Experimentar uma representação visual de relações sem alterar o banco Room do aplicativo.

### Distinção crítica
Obsidian não é automaticamente uma visualização ao vivo do banco Room do NEXUS. O grafo representa arquivos Markdown e seus links internos; não substitui consultas relacionais, integridade referencial, transações ou o modelo de dados do aplicativo.

Uma possível integração exigiria um fluxo explícito de exportação/geração de notas Markdown, identificadores estáveis, regras de atualização, tratamento de conflitos, privacidade e política de exclusão. Sincronização bidirecional seria uma etapa muito mais complexa e não deve ser presumida.

### Risco de escala
Um grafo global com muitos registros pode ficar visualmente poluído. A pesquisa deve avaliar Graph View global versus local, filtros, agrupamentos, propriedades, Bases e possíveis limites de desempenho. A utilidade deve ser testada com um conjunto sintético representativo, não presumida.

## 4. Alternativas a comparar

- Obsidian como visualizador externo somente leitura de uma exportação Markdown.
- MkDocs/Docusaurus para documentação navegável, sem grafo relacional.
- Mermaid ou diagramas gerados a partir de relações selecionadas.
- Uma visualização nativa no NEXUS, caso exista requisito real de explorar relações entre registros do próprio app.
- Não implementar integração; manter apenas links e documentação convencional.

## 5. Perguntas de pesquisa

1. Quais entidades/relacionamentos do NEXUS seriam realmente úteis de visualizar em grafo?
2. O objetivo é explorar documentação técnica, dados pessoais do usuário, ou ambos? Esses domínios não devem ser misturados sem justificativa.
3. Quais dados podem sair do armazenamento local? Quais precisam permanecer privados/offline?
4. Como gerar Markdown sem duplicar ou corromper a fonte de verdade?
5. Como manter IDs e links estáveis quando registros são renomeados, arquivados ou excluídos?
6. Como filtrar o grafo por módulo, tipo, período e profundidade?
7. Obsidian oferece valor superior a diagramas e telas nativas para o caso de uso real?
8. Que parte da documentação atual se beneficiaria de Diátaxis sem criar redundância?

## 6. Experimento sugerido — ainda não autorizado

1. Selecionar pequeno conjunto fictício de 20–30 entidades e relações.
2. Gerar notas Markdown com frontmatter/propriedades e links internos estáveis.
3. Abrir como vault local no Obsidian.
4. Testar Graph View global/local, filtros e Bases.
5. Comparar com Mermaid e uma representação tabular.
6. Avaliar clareza, ruído visual, facilidade de navegação, desempenho e esforço de manutenção.
7. Apresentar resultados antes de propor integração real.

Nenhum dado pessoal ou backup real deve ser exportado para esse experimento inicial.

## 7. Critérios para avançar

- Problema de navegação/visualização específico e observado.
- Benefício que não seja atendido adequadamente por links, tabelas ou diagramas simples.
- Fonte de verdade e direção de sincronização claramente definidas.
- Privacidade, offline-first e segurança preservados.
- Protótipo com dados fictícios demonstrando utilidade.
- Aprovação explícita antes de alterar código, banco, backup ou integração.

## 8. Referências iniciais

- Diátaxis: https://diataxis.fr/
- Diátaxis — aplicação: https://www.diataxis.fr/application/
- Obsidian Graph View: https://obsidian.md/help/plugins/graph
- Obsidian Bases: https://obsidian.md/help/bases
- MkDocs: https://www.mkdocs.org/
- Docusaurus: https://docusaurus.io/docs

## 9. Limites desta captura

- Não houve alteração no código Android, Room, backup, APIs, navegação ou interface.
- Não foi instalado nem conectado Obsidian.
- Não houve exportação de dados do NEXUS.
- A proposta não altera ADRs, requisitos ou roadmap técnico vigente.
- A ideia permanece em pesquisa; nenhuma implementação foi aprovada.
