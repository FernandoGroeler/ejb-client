package br.trabalho;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MainStateless {
	public static void main(String[] args) {
		MainStateless ms = new MainStateless();
	}
	
	private void addProduto() {
		ISessionBeansStatelessRemote remote;
		try {
			InitialContext ctx = new InitialContext(JNDIConfig.getConfig());
			remote = (ISessionBeansStatelessRemote) ctx.lookup("ejb:/ejb-estoque/SessionBeansStateless!br.aula.ISessionBeansStatelessRemote");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	
}