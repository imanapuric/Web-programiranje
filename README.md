# Member Book – Web aplikacija za upravljanje članovima biblioteke

Ova aplikacija je razvijena u okviru predmeta iz oblasti razvoja softvera, a predstavlja jednostavan sistem za upravljanje članovima i knjigama biblioteke. 
Izrađena je pomoću okvira Spring Boot, a koristi Thymeleaf za prikaz podataka i MySQL bazu podataka za pohranu informacija.
Članovi: Imana Purić i Amina Subašić
---

## Opis projekta

Aplikacija omogućava:

- Pregled svih članova biblioteke
- Pregled i upravljanje knjigama
- Evidenciju knjiga koje su zadužili članovi
- Posuđivanje i vraćanje knjiga
- Automatsko učitavanje početnih podataka u bazu (demo podaci)

Cilj projekta je prikaz osnovne CRUD funkcionalnosti (kreiranje, čitanje, ažuriranje, brisanje) i praktična primjena Spring Boot okvira u 
kombinaciji sa slojevima Controller–Service–Repository.

---

## Struktura projekta
Member_Book/
├── src/
│ ├── main/
│ │ ├── java/org/example/member_book/
│ │ │ ├── controller/ -> kontroleri (AppController, CategoryRestController)
│ │ │ ├── data/ -> učitavanje početnih podataka (DemoData.java)
│ │ │ ├── model/ -> entiteti (Book, Member, Category)
│ │ │ ├── repository/ -> JPA repozitoriji
│ │ │ ├── service/ -> poslovna logika aplikacije
│ │ │ └── MemberBookApplication.java
│ │ └── resources/
│ │ ├── templates/ -> HTML stranice (Thymeleaf)
│ │ ├── static/ -> statički sadržaji (ako ih ima)
│ │ └── application.properties
│ └── test/ -> testne klase
├── pom.xml
└── README.md


---

## Tehnologije

- Java 25  
- Spring Boot 3.5.7  
- Spring Data JPA  
- MySQL  
- Hibernate ORM  
- Thymeleaf  
- Maven  
- IntelliJ IDEA  

---

## Konfiguracija baze podataka

Prije pokretanja aplikacije, potrebno je da MySQL server bude aktivan i da postoji baza pod nazivom **member_book**.

U fajlu `application.properties` podesiti podatke o konekciji:

## Properties
spring.application.name=Member_Book

spring.datasource.url=jdbc:mysql://localhost:3306/member_book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Imana123!
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


## Host na kojem se pokreće
http://localhost:8080/members





