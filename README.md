# 🏢 Nevitech Insurance App

Bu proje, sigorta kampanyalarını yönetmek için geliştirilen bir **Spring Boot** tabanlı RESTful API uygulamasıdır. Kampanya oluşturma, durum değiştirme, geçmişi görüntüleme ve istatistik alma gibi temel özellikleri destekler.

---

## 📦 Proje Özellikleri

- ✅ Kampanya oluşturma (`POST /api/campaigns`)
- 🔁 Kampanya durum güncelleme (`PATCH /api/campaigns/{id}/status`)
- 📜 Kampanya durum geçmişi görüntüleme (`GET /api/campaigns/{id}/status-history`)
- 📊 Kampanya durum istatistikleri (`GET /api/campaigns/dashboard/classifieds/statistics`)
- 📁 Tüm kampanyaları listeleme (`GET /api/campaigns/all`)

---

## 🔧 Teknolojiler

- **Java 17**
- **Spring Boot 3.5.x**
- **Spring Web**
- **Spring Data JPA**
- **H2 In-Memory Database**
- **Lombok**
- **Maven**
- **JUnit 5 / Mockito**

---

## 🚀 Projeyi Çalıştırma

### 1. Maven ile Derleme

```bash
mvn clean install
```

### 2. Spring Boot Uygulamasını Başlat

```bash
mvn spring-boot:run
```

### 3. H2 Console (Varsayılan)

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:mem:testdb`
- Kullanıcı adı: `sa`
- Şifre: *(boş bırak)*

---

## 🧪 API Endpoint Örnekleri

### 🔹 POST /api/campaigns

```json
{
  "title": "Hayat Sigortası",
  "description": "30 yaş altı bireylere özel indirim",
  "category": "HAYAT"
}
```

### 🔹 PATCH /api/campaigns/{id}/status

```http
PATCH /api/campaigns/1/status?status=IPTAL
```

### 🔹 GET /api/campaigns/{id}/status-history

Kampanyanın durum değişiklik geçmişini verir.

### 🔹 GET /api/campaigns/all

Tüm kampanyaları listeler.

### 🔹 GET /api/campaigns/dashboard/classifieds/statistics

Kampanya durumlarına göre istatistikleri getirir.

---

## 🧪 Test

```bash
mvn test
```

- Integration test: `CampaignControllerIntegrationTest`
- Unit test: `CampaignServiceTest`
--

## ⚠️ Notlar

- Bu proje bir **POC** çalışmasıdır.
- Kimlik doğrulama (authentication) mekanizması **varsayılmıştır**.
- Aynı kullanıcı kampanya oluşturabilir ve onaylayabilir.

---

## 👨‍💻 Geliştirici

**İbrahim Akbaş**  
📧 ibrahimakbass00@gmail.com  
🔗 [LinkedIn](https://linkedin.com/in/ibrahimakbas)

---


---

## 🌐 Canlı Test Domaini

Uygulama Railway üzerinden canlıya alınmıştır:

🔗 [https://inssuranceapp-production.up.railway.app/](https://inssuranceapp-production.up.railway.app/)

### 📘 Swagger Dokümantasyonu

Swagger UI (API Dokümantasyonu) için doğrudan aşağıdaki adrese gidebilirsiniz:

🔗 [https://inssuranceapp-production.up.railway.app/swagger-ui/index.html](https://inssuranceapp-production.up.railway.app/swagger-ui/index.html)

