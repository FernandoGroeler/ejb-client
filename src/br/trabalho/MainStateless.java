package br.trabalho;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

public class MainStateless {
	public static void main(String[] args) {
		MainStateless ms = new MainStateless();
		ms.addProduto();
	}

	private void addProduto() {
		ISessionBeansStatelessRemote remote;
		ISessionBeansStatelessRemoteCarrinho car;

		try {
			InitialContext ctx = new InitialContext(JNDIConfig.getConfig());
			remote = (ISessionBeansStatelessRemote) ctx
					.lookup("ejb:/ejb-estoque/SessionBeansStateless!br.trabalho.ISessionBeansStatelessRemote");
			car = (ISessionBeansStatelessRemoteCarrinho) ctx
					.lookup("ejb:/ejb-carrinho/SessionBeansStateless!br.trabalho.ISessionBeansStatelessRemoteCarrinho");

			boolean continuar = true;
			while (continuar) {

				int idProduto = Integer
						.parseInt(JOptionPane.showInputDialog("Para adicionar um produto escolha as opções abaixo:"
								+ "\n 1 - Camisa" + "\n 2 - Calça" + "\n 3 - Tenis" + "\n 4 - Camiseta" + "\n 5 - Meia"
								+ "\n 6 - Listar Carrinho" + "\n 7 - Sair"));

				if (idProduto == 7) {
					continuar = false;
				} else {
					if (idProduto == 6) {
						for (Produto pro : car.searchProdutos()) {
							System.out.println(pro.getDescricao());
						}
						System.out.println("\n");
					} else {
						Produto produto = remote.getProduto(idProduto);

						if (produto.getEstoque() > 0) {
							car.addProduto(produto);

						} else {
							System.out.println("O produto não esta disponivel ou sem estoque");
						}
					}

				}

			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}