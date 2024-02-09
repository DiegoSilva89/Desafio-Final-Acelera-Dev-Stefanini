package com.stefanini.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController /*para spring MVC entender a classe é um controller de uma API Rest*/
@RequestMapping("/hello") /*Para o spring entender o mapeamento*/
public class HelloController {

    /*método para retornar a String para requisições do tipo Get em "/hello"*/
    @GetMapping
    public String olaMundo() {
        return "Hello world Spring";
    }
}

/*para reiniciar de forma automática, precisamos configurar o projeto para o DevTools funcionar.

Para incluir essa configuração, usaremos o atalho "Shift + Shift". Será exibida uma tela, que na parte superior temos
um ícone de lupa com um campo de busca que digitaremos "settings". Nesta opção informa que podemos usar o atalho
"Ctrl + Alt + S" para abrir a tela de configurações.

Na tela aberta, no menu do lado esquerdo, clicaremos na seta da opção "Build, Execution, Deployment" para expandir.
Nela, clicaremos na seção "Compiler", e à direita do menu, temos uma checkbox ("caixa de seleção") chamada "Build
project automatically" ("Construir projeto automaticamente").

Marcaremos a opção "Build project automatically" e depois vamos clicar no botão "Apply", no canto inferior direito da tela.
Agora, voltando para o menu do lado esquerdo, clicaremos na opção "Advanced Settings" ("Configurações avançadas").

Na página de configurações avançadas, marcaremos a opção "Allow auto-make to start even if developed application is
currently running" ("Permitir que a criação automática inicie mesmo se o aplicativo desenvolvido estiver em execução
no momento"), dentro da seção "Compiler".

Logo após, podemos clicar no botão "Apply" e depois em "Ok", no canto inferior direito.

*/
