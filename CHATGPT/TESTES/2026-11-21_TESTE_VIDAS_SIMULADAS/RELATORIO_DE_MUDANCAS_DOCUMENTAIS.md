# Relatório de mudanças documentais — Experimento das três vidas simuladas

**Data:** 21/11/2026  
**Escopo:** organização da memória estratégica e dos registros do experimento no repositório `NEXUS-DOCUMENTACAO`.  
**Código do aplicativo:** não alterado por esta operação documental.

## Por que a organização foi criada

O experimento gerou materiais de naturezas diferentes: uma instrução encaminhada ao Google AI Studio, três contextos humanos, um relatório técnico de geração/validação, observações que ainda serão coletadas durante o uso e possíveis ideias futuras.

Guardar tudo em um único documento tornaria difícil distinguir contexto, evidência, resultado e interpretação. Criar um segundo catálogo de ideias dentro da pasta do experimento, por outro lado, duplicaria a função de `CHATGPT/03_IDEIAS_E_BRAINSTORMS.md`, que já é o catálogo estratégico central.

A solução adotada foi separar os registros do experimento e manter as ideias no catálogo existente.

## Mudanças realizadas

1. Criada a pasta datada `CHATGPT/TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/`.
2. Criado um README para explicar objetivo, arquivos, limites e procedimento de teste.
3. Criada uma pasta para preservar o escopo do prompt. Como o texto integral original não pôde ser recuperado, o documento identifica explicitamente uma reconstrução fiel, sem alegar transcrição literal.
4. Criada uma pasta com um documento para cada perfil, preservando os contextos humanos e distinguindo-os de especificações técnicas.
5. Criado `REGISTRO_DO_EXPERIMENTO.md` para acompanhar etapas, evidências, observações e próximos passos.
6. Criado `RELATORIO_GERACAO_E_VALIDACAO.md` para registrar o resultado informado pelo Google AI Studio e suas limitações.
7. Criado `RELATORIO_TESTES_NO_APLICATIVO.md` como espaço de registro futuro, sem preencher resultados ainda não observados.
8. Criado este relatório para explicar motivação, decisões de organização e limites da mudança.

## O que foi deliberadamente mantido fora desta pasta

- **Catálogo de ideias:** permanece centralizado em `CHATGPT/03_IDEIAS_E_BRAINSTORMS.md`. Uma observação do teste só será promovida a ideia após análise e verificação de que não existe registro equivalente.
- **Decisões estratégicas:** permanecem em `CHATGPT/04_DECISOES_ESTRATEGICAS.md`; observações ainda não são decisões.
- **Propostas ao AI Studio:** permanecem em `CHATGPT/05_PROPOSTAS_ENVIADAS_AI_STUDIO.md`, com referência ao experimento.
- **Documentação técnica vigente:** não foi alterada. Não há confirmação de mudança de implementação.
- **JSONs:** não foram duplicados neste repositório documental; devem permanecer no repositório ativo do aplicativo, em `/test-backups/`, após confirmação de publicação.

## Princípios preservados

- Uma observação não é automaticamente uma ideia.
- Uma ideia não é uma decisão aprovada.
- Uma validação estática não comprova restauração no Android.
- Um relatório recebido não é auditoria independente.
- O registro do experimento preserva a origem e as limitações das evidências.
- A pasta do experimento é um histórico datado; novos experimentos podem receber pastas próprias sem misturar resultados.

## Resultado da mudança

A memória do experimento fica organizada por função, com referências cruzadas e sem criar um segundo catálogo de ideias. A estrutura permite acrescentar observações após os testes no aplicativo sem reescrever o relatório de geração.

## Limites desta operação

Esta mudança atualiza apenas o repositório documental. Não altera o repositório ativo do aplicativo, não publica os JSONs, não executa testes Android e não atualiza artefatos internos do Google AI Studio.


## 2026-11-21 — Registro pós-exploração do perfil Gabriel

### Motivação

Após a exploração inicial do perfil Gabriel, surgiram informações de naturezas diferentes: resultado positivo de uso, defeito funcional relatado, correção visual solicitada, melhorias de produto e possibilidades de longo prazo. Misturar tudo no registro do experimento ou no catálogo de ideias tornaria mais difícil recuperar cada tipo de informação.

### Mudanças realizadas

1. Criada a pasta 05_OBSERVACOES_POS_TESTE dentro do experimento.
2. Criados documentos separados para problemas/correções, ideias de evolução, visão de longo prazo e resultado da exploração de Gabriel.
3. Registrado BUG-001 (perda de estado ao girar o dispositivo) como defeito relatado, com causa ainda desconhecida e correção não implementada.
4. Registrado COR-001 (remoção do N verde redundante) como correção visual solicitada, preservando o ícone atual N branco sobre fundo preto.
5. Registrados OBS-001 e OBS-002 para preservar a semântica dos alertas e o fluxo apreciado da Agenda.
6. Capturadas IDEA-001 a IDEA-011 e FUT-001 a FUT-003, sem classificá-las como aprovadas ou implementadas.
7. Atualizados os índices e documentos de acompanhamento da área CHATGPT. O catálogo central recebeu apenas resumos e referências; os detalhes continuam na pasta do experimento.
8. Atualizado o relatório de testes no aplicativo para refletir a exploração inicial de Gabriel, mantendo Helena e Ricardo pendentes.

### Relação com o ATLAS

Foi criado no repositório oficial ATLAS um estudo de caso derivado, em examples/case-studies/nexus-documentation-evolution/. Ele descreve o contexto, a estrutura de pastas, a rastreabilidade e as lições para pesquisa metodológica. Não altera os protocolos ou regras normativas do ATLAS.

### Verificações

- A presença dos três JSONs foi conferida no repositório ativo PROJETO-NEXUS-/test-backups/.
- Os arquivos JSON não foram duplicados no repositório documental.
- A alteração foi documental; nenhum código do aplicativo foi modificado.
- Nenhuma correção funcional ou visual foi implementada ou validada por esta operação.
- Nenhum prompt novo foi enviado ao Google AI Studio nesta etapa.
