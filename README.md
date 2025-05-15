# - Mülakat Platformu

## 📝 Proje Açıklaması

**App**, şirket içi veya dışı kullanıcıların sınavlara katılabildiği, sorulara yanıt verebildiği ve sonuçlarının değerlendirildiği **Spring Boot tabanlı bir mülakat platformudur**. Uygulama, kullanıcı yönetimi, soru yönetimi, sınav organizasyonu ve sonuç takibi gibi temel modülleri içerir.

## 🎯 Temel Özellikler

- JWT tabanlı kimlik doğrulama (Access ve Refresh Token)
- Rol bazlı yetkilendirme (`ADMIN`, `EMPLOYEE`, `SUPERVISOR`)
- Kullanıcı, departman ve kategori CRUD işlemleri
- Soru oluşturma, güncelleme ve sınavlara ekleme
- Kullanıcı cevap yönetimi
- Sınav planlama, değerlendirme ve listeleme
- DTO (Data Transfer Object) kullanımı
- SOLID prensiplerine uygun, katmanlı mimari yapı

## 🏗️ Mimari Yapı

Uygulama katmanlı bir yapı üzerine kurulmuştur:
com.berkanozcelik.atmacaapp
├── config → Security & genel konfigürasyon
├── controller → REST API endpoint'leri
├── dto → Veri transfer nesneleri
├── entity → JPA Entity tanımları
├── repository → Spring Data JPA repository arayüzleri
├── service → İş mantığı (business logic)
├── service.impl → Servislerin implementasyonları
├── exception → Özel hata sınıfları
└── security → JWT filtreleri, authentication servisleri


> Proje SOLID prensiplerine uygun olacak şekilde aşağıdaki tasarım kararlarını benimser:
> - **S**ingle Responsibility: Her sınıf yalnızca kendi sorumluluğuna odaklanır.
> - **O**pen/Closed: Servisler ve controller’lar genişlemeye açık, değişikliğe kapalıdır.
> - **L**iskov Substitution: Interface’ler doğru şekilde uygulanarak türetilmiş sınıflar kullanılabilir.
> - **I**nterface Segregation: Gereksiz metodlar içermeyen, ayrık interface yapıları
> - **D**ependency Inversion: `@Autowired` ile bağımlılıklar enjekte edilmiştir.

## 🔐 Kimlik Doğrulama

- `/register` - Kullanıcı kayıt
- `/authenticate` - Giriş ve token alma
- `/refreshToken` - Access token yenileme

JWT yapısı ile birlikte hem access hem refresh token güvenliği sağlanır.

## 📦 Postman Collection

Tüm endpointlerin test edilebileceği bir [Postman koleksiyonu](https://berkans-7487.postman.co/workspace/Berkans-Workspace~5c906657-c128-42f7-833c-5f7e1bee44d9/collection/43071018-a7d053e3-b75a-42d7-b9dc-02f61c6d2628?action=share&source=collection_link&creator=43071018) mevcuttur. Alternatif olarak bu repo ile birlikte `.json` dosyası olarak da bulunmaktadır.

## 📘 Örnek API Kullanımları

### ➕ Kullanıcı Kayıt

```http
POST /register
{
  "username": "john4",
  "email": "john3@example.com",
  "password": "123456",
  "userRole": "ADMIN",
  "department": { "id": 1 }
}

### 🔐 Giriş Yapma

POST /authenticate
{
  "username": "john4",
  "password": "123456"
}
### ❓ Soru Oluşturma
POST /questions/create
{
  "questionText": "Hangisi bir frontend framework değildir?",
  "questionType": "CLASSIC",
  "options": ["React", "Vue", "Spring", "Angular"],
  "correctOption": "Spring",
  "questionPoint": 10,
  "userId": 1,
  "departmentId": 1,
  "categoryId": 1
}
### 📝 Cevap Gönderme

💾 Teknolojiler
Java 17

Spring Boot 3.x

Spring Security & JWT

Spring Data JPA (Hibernate)

PostgreSQL / H2 (dev)

Lombok

MapStruct (isteğe bağlı DTO mapping)

Postman (API test)

🚀 Kurulum

git clone https://github.com/berkanozcelik/atmaca-app.git
cd atmaca-app
./mvnw spring-boot:run

📂 DTO Örnekleri

public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
    private Department department;
}

public class QuestionDto {
    private String questionText;
    private String questionType;
    private List<String> options;
    private String correctOption;
    private Integer questionPoint;
    private Long userId;
    private Long departmentId;
    private Long categoryId;
}
👨‍💻 Geliştirici
Berkan Özçelik
Email: berkanozcelik3.6@gmail.com
LinkedIn: linkedin.com/in/berkanozcelik


