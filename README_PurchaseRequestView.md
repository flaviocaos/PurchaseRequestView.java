# 📄 PurchaseRequestView.java - Visualização de Requisição de Compra

Esta ação implementa a **visualização detalhada (View)** de uma Requisição de Compra no módulo de Pedidos de Compra da plataforma FIRSTi. Atualizada para refletir corretamente os campos reais utilizados e a estrutura final da tela.

---

## 🧩 Pacote

```java
package br.com.firsti.packages.purchase.modules.purchaseRequest.actions;
```

---

## ✅ Funcionalidades Implementadas

### 🧾 Campos Exibidos

#### Cabeçalho (Header)

| Campo        | Campo lógico         | CSS     | Descrição                           |
|--------------|----------------------|---------|-------------------------------------|
| Empresa      | company              | col-4   | Nome da empresa                     |
| Depósito     | warehouse            | col-6   | Nome do depósito                    |
| Status       | status               | col-2   | Status atual da requisição          |
| Requisitante | requester            | col-10  | Nome do colaborador que requisitou |
| Criação      | creation             | col-2   | Data/hora de criação da requisição  |

#### Corpo (Body)

| Campo        | Campo lógico         | CSS     | Descrição                           |
|--------------|----------------------|---------|-------------------------------------|
| Categoria    | category             | col-2   | Categoria do produto                |
| Tipo         | productType          | col-4   | Tipo de produto                     |
| Produto      | productName          | col-4   | Modelo do produto                   |
| Quantidade   | quantity             | col-2   | Quantidade requisitada             |
| Descrição    | description          | col-12  | Detalhamento adicional da requisição |

#### Rodapé (Footer)

- Exibe o botão **"Ver compra"** (`viewPurchase`) com estilo `btn-primary col-2`
- Botão é exibido **apenas** se `purchaseRequest.getPurchase() != null`
- Abre a ação `StockMovementView` em janela modal

---

## 🔧 Classes e Elementos Utilizados

- `PurchaseRequest` — entidade base
- `Warehouse`, `Company`, `ProductType`, `Product` — entidades associadas
- `InputView`, `Textarea`, `ElementGroup`, `Button` — elementos UI
- `StockMovementView` — ação modal associada ao botão de rodapé

---

## ✔️ Atualizações Realizadas

- Corrigido o nome do campo de produto (`productName` → model)
- Condições seguras com verificações `!= null`
- Layout refinado e agrupamento claro no `Body`

---

## 🛡️ Segurança

- Acesso limitado a `Access.COMPANY_PRIVATE`
- Exceção lançada se a requisição não for encontrada

---

Este componente garante uma visualização rica e responsiva, pronta para integrar-se aos fluxos da plataforma com segurança e clareza.
