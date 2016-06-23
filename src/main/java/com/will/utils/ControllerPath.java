package com.will.utils;

public class ControllerPath {
	
	/** Criando as constantes que vão armazenar as URI do sistema. 
	 * Dessa forma não precisa alterar nos controllers quando precisar mudar URI.
	*/
	
	public static final String ALL =" /** ";
	
	public static final String ROOT_PATH =" /api ";
	
	public static final String PUBLIC_ROOT_PATH = ROOT_PATH +" /public ";
	
	public static final String PRIVATE_ROOT_PATH = ROOT_PATH +" /private ";
	
	public static final String CLIENTE_PATH = PRIVATE_ROOT_PATH +" /cliente ";
	
	public static final String CIDADE_PATH = PRIVATE_ROOT_PATH +" /cidade ";
	
	public static final String PLANO_ACESSO_PATH = PRIVATE_ROOT_PATH +" /plano ";

}
