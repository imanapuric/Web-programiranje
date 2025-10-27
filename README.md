# 📚 BIBLIOTEKA GRADA – Member & Book

## 👩‍💻 Autori
- **Imana Purić**
- **Amina Subašić**

---

## 🎯 Opis projekta
Ova mini **MVC Spring Boot** aplikacija predstavlja simulaciju **gradske biblioteke**, razvijenu u okviru laboratorijske vježbe iz predmeta *Web programiranje*.

Cilj aplikacije je demonstrirati:
- primjenu **Spring Boot MVC arhitekture**  
- rad s **dvije povezane klase (Model A i Model B)**  
- korištenje **Thymeleaf** za prikaz podataka  
- jednostavnu interaktivnu funkcionalnost (posudba i vraćanje knjiga)

---

## 🧩 Modeli i relacija

### 🧍 Model A – `Member`
Predstavlja člana biblioteke.  
Sadrži atribute:
- `id` – jedinstveni identifikator  
- `fullName` – ime i prezime člana  
- `email` – e-mail adresa  
- `membershipLevel` – nivo članstva (npr. standard, premium)  
- `yearJoined` – godina pristupanja biblioteci  

### 📖 Model B – `Book`
Predstavlja knjigu dostupnu u biblioteci.  
Sadrži atribute:
- `id` – jedinstveni identifikator  
- `title` – naziv knjige  
- `author` – autor  
- `genre` – žanr  
- `available` – status (true = dostupna, false = posuđena)  
- `borrowedBy` – referenca na člana koji je trenutno posudio knjigu  

---

### 🔗 Relacija
Između modela **Member** i **Book** postoji relacija **1:N (jedan član može posuditi više knjiga)**.  
Ta veza je realizovana kroz polje `borrowedBy` u klasi `Book`, koje pokazuje na objekat tipa `Member`.

---

## ⚙️ Funkcionalnosti
- Prikaz liste članova (`/members`)
- Prikaz liste knjiga (`/books`)
- Akcijska stranica svakog člana – posuđivanje i vraćanje knjiga
- Demo podaci učitani kroz `DemoData.java` (bez baze podataka)

<img width="1312" height="883" alt="memberBook" src="https://github.com/user-attachments/assets/6e8cb4f1-b991-4103-a87d-ccdc154b7286" />



