<?xml version="1.0" encoding="UTF-8"?>
<bpmn:definitions
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:bpmn="http://www.omg.org/spec/BPMN/20100524/MODEL"
  xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
  xmlns:dc="http://www.omg.org/spec/DD/20100524/DC"
  xmlns:di="http://www.omg.org/spec/DD/20100524/DI"
  id="Definitions_Prodesp"
  targetNamespace="https://prodesp.example/bpmn">

  <bpmn:process id="Process_AcessoPortal" name="Acesso ao Portal de Editais Prodesp" isExecutable="false">
    <bpmn:startEvent id="Start_Inicio" name="Início"/>
    <bpmn:task id="Task_AcessarPortal" name="Acessar o portal"/>
    <bpmn:exclusiveGateway id="Gateway_PossuiCadastro" name="Possui cadastro?"/>

    <bpmn:task id="Task_PreencherCadastro" name="Preencher formulário de cadastro"/>
    <bpmn:task id="Task_ValidarCadastro" name="Validar dados e CAPTCHA"/>
    <bpmn:exclusiveGateway id="Gateway_CadastroValido" name="Cadastro válido?"/>
    <bpmn:task id="Task_RegistrarUsuario" name="Registrar usuário no banco de dados"/>
    <bpmn:task id="Task_ErroCadastro" name="Informar erro no cadastro"/>

    <bpmn:task id="Task_AbrirLogin" name="Abrir tela de login"/>
    <bpmn:task id="Task_InformarCredenciais" name="Informar usuário, CPF/CNPJ e senha"/>
    <bpmn:task id="Task_ValidarCredenciais" name="Validar CAPTCHA e credenciais"/>
    <bpmn:exclusiveGateway id="Gateway_CredenciaisValidas" name="Credenciais válidas?"/>
    <bpmn:task id="Task_ErroLogin" name="Informar falha e registrar tentativa"/>

    <bpmn:exclusiveGateway id="Gateway_2FAAtivo" name="2FA ativo?"/>
    <bpmn:exclusiveGateway id="Gateway_Metodo2FA" name="Método configurado"/>
    <bpmn:task id="Task_InformarTOTP" name="Informar código do aplicativo autenticador"/>
    <bpmn:task id="Task_EnviarSMS" name="Enviar código por SMS"/>
    <bpmn:task id="Task_InformarSMS" name="Informar código recebido por SMS"/>
    <bpmn:task id="Task_Validar2FA" name="Validar segundo fator"/>
    <bpmn:exclusiveGateway id="Gateway_CodigoValido" name="Código válido?"/>
    <bpmn:task id="Task_Erro2FA" name="Informar código inválido ou tentativa bloqueada"/>

    <bpmn:task id="Task_EmitirSessao" name="Emitir cookie de sessão com JWT"/>
    <bpmn:task id="Task_CarregarPainel" name="Redirecionar e carregar painel conforme perfil"/>
    <bpmn:endEvent id="End_AcessoConcedido" name="Acesso ao painel concedido"/>

    <bpmn:sequenceFlow id="Flow_01" sourceRef="Start_Inicio" targetRef="Task_AcessarPortal"/>
    <bpmn:sequenceFlow id="Flow_02" sourceRef="Task_AcessarPortal" targetRef="Gateway_PossuiCadastro"/>
    <bpmn:sequenceFlow id="Flow_03" name="Não" sourceRef="Gateway_PossuiCadastro" targetRef="Task_PreencherCadastro"/>
    <bpmn:sequenceFlow id="Flow_04" sourceRef="Task_PreencherCadastro" targetRef="Task_ValidarCadastro"/>
    <bpmn:sequenceFlow id="Flow_05" sourceRef="Task_ValidarCadastro" targetRef="Gateway_CadastroValido"/>
    <bpmn:sequenceFlow id="Flow_06" name="Sim" sourceRef="Gateway_CadastroValido" targetRef="Task_RegistrarUsuario"/>
    <bpmn:sequenceFlow id="Flow_07" sourceRef="Task_RegistrarUsuario" targetRef="Task_AbrirLogin"/>
    <bpmn:sequenceFlow id="Flow_08" name="Sim" sourceRef="Gateway_PossuiCadastro" targetRef="Task_AbrirLogin"/>
    <bpmn:sequenceFlow id="Flow_09" name="Não" sourceRef="Gateway_CadastroValido" targetRef="Task_ErroCadastro"/>
    <bpmn:sequenceFlow id="Flow_10" sourceRef="Task_ErroCadastro" targetRef="Task_PreencherCadastro"/>

    <bpmn:sequenceFlow id="Flow_11" sourceRef="Task_AbrirLogin" targetRef="Task_InformarCredenciais"/>
    <bpmn:sequenceFlow id="Flow_12" sourceRef="Task_InformarCredenciais" targetRef="Task_ValidarCredenciais"/>
    <bpmn:sequenceFlow id="Flow_13" sourceRef="Task_ValidarCredenciais" targetRef="Gateway_CredenciaisValidas"/>
    <bpmn:sequenceFlow id="Flow_14" name="Não" sourceRef="Gateway_CredenciaisValidas" targetRef="Task_ErroLogin"/>
    <bpmn:sequenceFlow id="Flow_15" sourceRef="Task_ErroLogin" targetRef="Task_InformarCredenciais"/>
    <bpmn:sequenceFlow id="Flow_16" name="Sim" sourceRef="Gateway_CredenciaisValidas" targetRef="Gateway_2FAAtivo"/>

    <bpmn:sequenceFlow id="Flow_17" name="Não" sourceRef="Gateway_2FAAtivo" targetRef="Task_EmitirSessao"/>
    <bpmn:sequenceFlow id="Flow_18" name="Sim" sourceRef="Gateway_2FAAtivo" targetRef="Gateway_Metodo2FA"/>
    <bpmn:sequenceFlow id="Flow_19" name="Aplicativo autenticador" sourceRef="Gateway_Metodo2FA" targetRef="Task_InformarTOTP"/>
    <bpmn:sequenceFlow id="Flow_20" name="SMS" sourceRef="Gateway_Metodo2FA" targetRef="Task_EnviarSMS"/>
    <bpmn:sequenceFlow id="Flow_21" sourceRef="Task_EnviarSMS" targetRef="Task_InformarSMS"/>
    <bpmn:sequenceFlow id="Flow_22" sourceRef="Task_InformarTOTP" targetRef="Task_Validar2FA"/>
    <bpmn:sequenceFlow id="Flow_23" sourceRef="Task_InformarSMS" targetRef="Task_Validar2FA"/>
    <bpmn:sequenceFlow id="Flow_24" sourceRef="Task_Validar2FA" targetRef="Gateway_CodigoValido"/>
    <bpmn:sequenceFlow id="Flow_25" name="Sim" sourceRef="Gateway_CodigoValido" targetRef="Task_EmitirSessao"/>
    <bpmn:sequenceFlow id="Flow_26" name="Não" sourceRef="Gateway_CodigoValido" targetRef="Task_Erro2FA"/>
    <bpmn:sequenceFlow id="Flow_27" sourceRef="Task_Erro2FA" targetRef="Gateway_Metodo2FA"/>

    <bpmn:sequenceFlow id="Flow_28" sourceRef="Task_EmitirSessao" targetRef="Task_CarregarPainel"/>
    <bpmn:sequenceFlow id="Flow_29" sourceRef="Task_CarregarPainel" targetRef="End_AcessoConcedido"/>
  </bpmn:process>

  <bpmndi:BPMNDiagram id="Diagram_AcessoPortal">
    <bpmndi:BPMNPlane id="Plane_AcessoPortal" bpmnElement="Process_AcessoPortal">
      <bpmndi:BPMNShape id="Shape_Start" bpmnElement="Start_Inicio"><dc:Bounds x="80" y="180" width="36" height="36"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Acessar" bpmnElement="Task_AcessarPortal"><dc:Bounds x="150" y="158" width="120" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_PossuiCadastro" bpmnElement="Gateway_PossuiCadastro" isMarkerVisible="true"><dc:Bounds x="310" y="173" width="50" height="50"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_PreencherCadastro" bpmnElement="Task_PreencherCadastro"><dc:Bounds x="400" y="320" width="150" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_ValidarCadastro" bpmnElement="Task_ValidarCadastro"><dc:Bounds x="600" y="320" width="150" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_CadastroValido" bpmnElement="Gateway_CadastroValido" isMarkerVisible="true"><dc:Bounds x="800" y="335" width="50" height="50"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Registrar" bpmnElement="Task_RegistrarUsuario"><dc:Bounds x="900" y="320" width="160" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_ErroCadastro" bpmnElement="Task_ErroCadastro"><dc:Bounds x="600" y="450" width="150" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Login" bpmnElement="Task_AbrirLogin"><dc:Bounds x="1120" y="158" width="120" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_InformarCred" bpmnElement="Task_InformarCredenciais"><dc:Bounds x="1280" y="158" width="160" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_ValidarCred" bpmnElement="Task_ValidarCredenciais"><dc:Bounds x="1480" y="158" width="160" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_GCred" bpmnElement="Gateway_CredenciaisValidas" isMarkerVisible="true"><dc:Bounds x="1680" y="173" width="50" height="50"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_ErroLogin" bpmnElement="Task_ErroLogin"><dc:Bounds x="1680" y="300" width="160" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_G2FA" bpmnElement="Gateway_2FAAtivo" isMarkerVisible="true"><dc:Bounds x="1790" y="173" width="50" height="50"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_GMetodo" bpmnElement="Gateway_Metodo2FA" isMarkerVisible="true"><dc:Bounds x="1900" y="173" width="50" height="50"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_TOTP" bpmnElement="Task_InformarTOTP"><dc:Bounds x="2000" y="70" width="170" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_EnviarSMS" bpmnElement="Task_EnviarSMS"><dc:Bounds x="2000" y="300" width="140" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_InformarSMS" bpmnElement="Task_InformarSMS"><dc:Bounds x="2180" y="300" width="150" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Validar2FA" bpmnElement="Task_Validar2FA"><dc:Bounds x="2220" y="158" width="140" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_GCodigo" bpmnElement="Gateway_CodigoValido" isMarkerVisible="true"><dc:Bounds x="2410" y="173" width="50" height="50"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Erro2FA" bpmnElement="Task_Erro2FA"><dc:Bounds x="2400" y="300" width="180" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Sessao" bpmnElement="Task_EmitirSessao"><dc:Bounds x="2520" y="158" width="150" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_Painel" bpmnElement="Task_CarregarPainel"><dc:Bounds x="2720" y="158" width="160" height="80"/></bpmndi:BPMNShape>
      <bpmndi:BPMNShape id="Shape_End" bpmnElement="End_AcessoConcedido"><dc:Bounds x="2930" y="180" width="36" height="36"/></bpmndi:BPMNShape>

      <bpmndi:BPMNEdge id="Edge_01" bpmnElement="Flow_01"><di:waypoint x="116" y="198"/><di:waypoint x="150" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_02" bpmnElement="Flow_02"><di:waypoint x="270" y="198"/><di:waypoint x="310" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_03" bpmnElement="Flow_03"><di:waypoint x="335" y="223"/><di:waypoint x="335" y="360"/><di:waypoint x="400" y="360"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_04" bpmnElement="Flow_04"><di:waypoint x="550" y="360"/><di:waypoint x="600" y="360"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_05" bpmnElement="Flow_05"><di:waypoint x="750" y="360"/><di:waypoint x="800" y="360"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_06" bpmnElement="Flow_06"><di:waypoint x="850" y="360"/><di:waypoint x="900" y="360"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_07" bpmnElement="Flow_07"><di:waypoint x="1060" y="360"/><di:waypoint x="1090" y="360"/><di:waypoint x="1090" y="198"/><di:waypoint x="1120" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_08" bpmnElement="Flow_08"><di:waypoint x="360" y="198"/><di:waypoint x="1120" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_09" bpmnElement="Flow_09"><di:waypoint x="825" y="385"/><di:waypoint x="825" y="490"/><di:waypoint x="750" y="490"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_10" bpmnElement="Flow_10"><di:waypoint x="600" y="490"/><di:waypoint x="475" y="490"/><di:waypoint x="475" y="400"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_11" bpmnElement="Flow_11"><di:waypoint x="1240" y="198"/><di:waypoint x="1280" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_12" bpmnElement="Flow_12"><di:waypoint x="1440" y="198"/><di:waypoint x="1480" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_13" bpmnElement="Flow_13"><di:waypoint x="1640" y="198"/><di:waypoint x="1680" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_14" bpmnElement="Flow_14"><di:waypoint x="1705" y="223"/><di:waypoint x="1705" y="300"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_15" bpmnElement="Flow_15"><di:waypoint x="1680" y="340"/><di:waypoint x="1360" y="340"/><di:waypoint x="1360" y="238"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_16" bpmnElement="Flow_16"><di:waypoint x="1730" y="198"/><di:waypoint x="1790" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_17" bpmnElement="Flow_17"><di:waypoint x="1815" y="173"/><di:waypoint x="1815" y="100"/><di:waypoint x="2595" y="100"/><di:waypoint x="2595" y="158"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_18" bpmnElement="Flow_18"><di:waypoint x="1840" y="198"/><di:waypoint x="1900" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_19" bpmnElement="Flow_19"><di:waypoint x="1925" y="173"/><di:waypoint x="1925" y="110"/><di:waypoint x="2000" y="110"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_20" bpmnElement="Flow_20"><di:waypoint x="1925" y="223"/><di:waypoint x="1925" y="340"/><di:waypoint x="2000" y="340"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_21" bpmnElement="Flow_21"><di:waypoint x="2140" y="340"/><di:waypoint x="2180" y="340"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_22" bpmnElement="Flow_22"><di:waypoint x="2170" y="110"/><di:waypoint x="2290" y="110"/><di:waypoint x="2290" y="158"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_23" bpmnElement="Flow_23"><di:waypoint x="2330" y="340"/><di:waypoint x="2350" y="340"/><di:waypoint x="2350" y="238"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_24" bpmnElement="Flow_24"><di:waypoint x="2360" y="198"/><di:waypoint x="2410" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_25" bpmnElement="Flow_25"><di:waypoint x="2460" y="198"/><di:waypoint x="2520" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_26" bpmnElement="Flow_26"><di:waypoint x="2435" y="223"/><di:waypoint x="2435" y="300"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_27" bpmnElement="Flow_27"><di:waypoint x="2400" y="340"/><di:waypoint x="1925" y="340"/><di:waypoint x="1925" y="223"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_28" bpmnElement="Flow_28"><di:waypoint x="2670" y="198"/><di:waypoint x="2720" y="198"/></bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge id="Edge_29" bpmnElement="Flow_29"><di:waypoint x="2880" y="198"/><di:waypoint x="2930" y="198"/></bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</bpmn:definitions>
