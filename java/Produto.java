fetch("/api/produto?id=51")
  .then(res => res.json())
  .then(prod => {
    document.getElementById("nome").textContent = prod.nome;
    document.getElementById("preco").textContent = "Preço: R$ " + parseFloat(prod.preco).toFixed(2);
    document.getElementById("quantidade").textContent = "Estoque disponível: " + prod.quantidade + " unidades";
  });

function comprar() {
  const qtd = parseInt(document.getElementById("qtd").value);
  if (qtd > 0) {
    alert("Pedido de compra realizado com sucesso!\nQuantidade: " + qtd);
    // Aqui você pode futuramente enviar via POST para um /api/compra
  } else {
    alert("Digite uma quantidade válida.");
  }
}

