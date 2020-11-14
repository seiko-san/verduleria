
/**
 * Author:  seiko
 * Created: 13-11-2020
 */

create database verduleria;
use verduleria;

create table vendedor(
id_vendedor int not null auto_increment,
nombre_vendedor varchar(30) not null,
apellido_vendedor varchar(30) not null,
nick_vendedor varchar(15) not null,
clave_vendedor varchar(15) not null,
primary key (id_vendedor));

insert into vendedor(nombre_vendedor,apellido_vendedor,nick_vendedor,clave_vendedor)
values ("darlyn","soazo","admin","123");