Feature: Login

  @loginAndroid
  Scenario: Login com sucesso
    Given o usuário está na tela de login
    When ele insere usuário "admin" e senha "1234"
    And toca no botão de login
    Then ele deve ver a tela inicial
