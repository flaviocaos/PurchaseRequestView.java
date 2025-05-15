# 📄 PurchaseRequestView.java - Visualização de Requisição de Compra

Esta ação implementa a **visualização detalhada (View)** de uma Requisição de Compra no módulo de Pedidos de Compra da plataforma FIRSTi. É derivada da classe `AbstractActionView` e exibe os principais campos informativos em uma janela modal com estrutura dividida em **cabeçalho (header)**, **corpo (body)** e **rodapé (footer)**.

---

## 🧩 Pacote

```java
package br.com.firsti.packages.purchase.modules.purchaseRequest.actions;
```

---

## ✅ Funcionalidades Implementadas

### 🧾 Campos Exibidos

#### Cabeçalho (Header)

| Campo        | Classe/Campo        | CSS     | Descrição                           |
|--------------|---------------------|---------|-------------------------------------|
| Empresa      | company             | col-4   | Nome da empresa                     |
| Depósito     | warehouse           | col-6   | Nome do depósito                    |
| Status       | status              | col-2   | Status atual da requisição          |
| Requisitante | requester           | col-10  | Nome do colaborador que requisitou |
| Criação      | creation            | col-2   | Data/hora de criação da requisição  |

#### Corpo (Body)

Exibido dentro de um `ElementGroup` chamado `"productInfo"` com os seguintes campos:

| Campo        | Classe/Campo        | CSS     | Descrição                           |
|--------------|---------------------|---------|-------------------------------------|
| Produto      | product             | col-12  | Nome do produto                     |
| Categoria    | category            | col-2   | Categoria do produto                |
| Tipo         | productType         | col-4   | Tipo de produto                     |
| Produto (2)  | productName         | col-4   | Nome detalhado do produto           |
| Quantidade   | quantity            | col-2   | Quantidade requisitada             |
| Descrição    | description         | col-12  | Descrição da requisição (textarea) |

#### Rodapé (Footer)

Exibe o botão **"Ver compra"** (`viewPurchase`) apenas se existir um vínculo com a entidade `Purchase`. Este botão:

- Possui estilo `btn-primary col-2`
- Abre a ação `StockMovementView` em uma janela modal via `ElementRequest.createModalRequest(...)`

---

## 🔧 Classes Utilizadas

- `PurchaseRequest` — Entidade principal da visualização
- `Company`, `Warehouse`, `Collaborator`, `Product`, `ProductType` — Entidades associadas
- `StockMovementView` — Ação chamada se houver vínculo com `Purchase`
- `InputView`, `Textarea`, `ElementGroup`, `Button` — Componentes visuais

---

## 🛡️ Segurança e Acesso

- O acesso é restrito a usuários com permissão `COMPANY_PRIVATE`
- Verificação de existência da entidade via `entityManager.find(...)`
- Geração de exceções customizadas como `ResourceNotFoundException`

---

## 📌 Observações

✔️ Interface responsiva com controle de layout por `col-*`  
✔️ Carregamento seguro dos dados via `getDataBuilder()`  
✔️ Estrutura compatível com o padrão modular do framework FIRSTi  

---

Se desejar adicionar suporte multilíngue, estilização customizada ou integrações adicionais com outros módulos, esta classe já está preparada para expansão.
