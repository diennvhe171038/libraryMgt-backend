CREATE DATABASE  IF NOT EXISTS `library_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_system`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: library_system
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `FK30kim6ktgxc1vj2s359wjlggg` (`created_by`),
  KEY `FKnp3s65pdoldgrriwoj4275ldh` (`updated_by`),
  CONSTRAINT `FK30kim6ktgxc1vj2s359wjlggg` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKnp3s65pdoldgrriwoj4275ldh` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'2024-06-29 14:26:49.049127','Andrew Hunt','2024-07-05 13:35:06.546618',4,4,'Andy Hunt là tác giả viết sách về phát triển phần mềm và khoa học viễn tưởng thú vị. Hunt là đồng tác giả của cuốn sách nổi tiếng \"The Pragmatic Programmer\", cuốn sách nổi tiếng \"Pragmatic Thinking & Learning\", \"Practices of an Agile Developer\" từng đoạt giải thưởng, nửa tá cuốn sách và nhiều bài báo khác. Andy là một trong 17 tác giả đầu tiên của Tuyên ngôn Agile. Ông và đồng tác giả Dave Thomas đã thành lập nhà xuất bản Pragmatic Bookshelf, chuyên về sách dành cho các nhà phát triển phần mềm, người thử nghiệm và nhà quản lý.'),(2,'2024-06-29 14:41:10.828841','David Thomas','2024-07-05 13:37:52.044476',4,4,NULL),(4,'2024-07-06 15:18:16.266068','Alex Banks','2024-07-06 15:18:16.266068',4,4,''),(5,'2024-07-06 15:18:53.239818','Eve Porcello','2024-07-06 15:18:53.239818',4,4,''),(6,'2024-07-06 15:24:21.606021','Robin Wieruch','2024-07-06 15:24:21.606021',4,4,'Robin Wieruch là một kỹ sư web và phần mềm người Đức, người chuyên học và dạy lập trình bằng JavaScript. Sau khi lấy được bằng thạc sĩ về khoa học máy tính, anh tiếp tục tự học hàng ngày. Anh ấy đã tích lũy được kinh nghiệm từ thế giới khởi nghiệp, nơi anh ấy sử dụng JavaScript quá mức trong thời gian làm việc và thời gian rảnh rỗi. Nó cho anh cơ hội dạy người khác về những chủ đề này.\n\nTrong một vài năm, Robin đã làm việc chặt chẽ với một nhóm kỹ sư giỏi tại một công ty có tên Cải tiến Nhỏ để phát triển các ứng dụng quy mô lớn. Công ty cung cấp sản phẩm SaaS cho phép khách hàng đưa ra phản hồi cho công ty của họ. Ứng dụng này được phát triển bằng cách sử dụng JavaScript ở giao diện người dùng và Java ở phần phụ trợ. Ở giao diện người dùng, lần lặp đầu tiên được viết bằng Java với Wicket Framework và jQuery. Khi thế hệ SPA đầu tiên trở nên phổ biến, công ty đã chuyển sang Angular 1.x cho ứng dụng giao diện người dùng. Sau khi sử dụng Angular hơn hai năm, rõ ràng Angular không phải là giải pháp tốt nhất để làm việc với các ứng dụng cường độ cao ngày trước. Đó là lý do tại sao công ty đã thực hiện bước nhảy vọt cuối cùng sang React và Redux, giúp công ty có thể hoạt động thành công trên quy mô lớn.\n\nTrong thời gian làm việc tại công ty, Robin thường xuyên viết bài về phát triển web trên trang web của mình. Anh ấy đã nhận được phản hồi tuyệt vời từ mọi người về các bài báo của mình và điều đó cho phép anh ấy cải thiện phong cách viết và giảng dạy của mình. Hết bài này đến bài khác, khả năng dạy dỗ người khác của Robin ngày càng tăng. Bài viết đầu tiên của anh ấy chứa quá nhiều thứ khiến học sinh cảm thấy choáng ngợp, nhưng anh ấy đã cải thiện nó theo thời gian bằng cách tập trung và chỉ dạy một môn học.\n\nNgày nay, Robin là một giáo viên tự kinh doanh. Anh nhận thấy đây là một hoạt động thú vị khi chứng kiến ​​học sinh phát triển bằng cách đưa ra cho các em những mục tiêu rõ ràng và vòng phản hồi ngắn. Đó là một điều bạn sẽ học được ở một công ty phản hồi, phải không? Nhưng nếu không tự mình viết mã, anh ấy sẽ không thể dạy mọi thứ. Đó là lý do anh đầu tư thời gian còn lại vào việc lập trình. Bạn có thể tìm thêm thông tin về Robin cũng như cách hỗ trợ và làm việc với anh ấy trên trang web của anh ấy.'),(7,'2024-07-06 15:33:32.954965','Shinichi Suzuki','2024-07-06 15:35:37.841215',4,4,''),(9,'2024-07-06 18:56:07.571703','Julia Cameron','2024-07-06 18:56:07.571703',4,4,'Julia Cameron đã là một nghệ sĩ tích cực trong hơn ba mươi năm. Cô là tác giả của hơn ba mươi cuốn sách, tiểu thuyết và phi hư cấu, bao gồm các tác phẩm bán chạy nhất về quá trình sáng tạo: Con đường nghệ sĩ, Đi bộ trong thế giới này, Đi tìm nước và Chế độ ăn kiêng viết lách. Là một tiểu thuyết gia, nhà viết kịch, nhạc sĩ và nhà thơ, cô có nhiều thành tích trong lĩnh vực sân khấu, điện ảnh và truyền hình.\n\nNỗ lực mới nhất: Julia Cameron Live, một khóa học trực tuyến và cộng đồng nghệ sĩ do Julia dẫn đầu. Đây là cuộc thảo luận toàn diện nhất mà cô từng thực hiện trên The Artist\'s Way và là lần đầu tiên cô cho phép camera vào nhà mình.');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `publication_year` int DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','AVAILABLE','BORROWED','DAMAGED','INACTIVE','LOST','OUT_OF_STOCK') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total_page` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `FKkjuhf186v8xfqqig9k2hsol5r` (`created_by`),
  KEY `FKd92cqwbn1a3eo00ov7dg9yhty` (`updated_by`),
  CONSTRAINT `FKd92cqwbn1a3eo00ov7dg9yhty` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkjuhf186v8xfqqig9k2hsol5r` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (28,'9780201616224','2024-07-06 14:17:12.602101','1720314653664.jpg','English',70000.00,1999,'Addison-Wesley Professional','ACTIVE','The Pragmatic Programmer: From Journeyman to Master',352,'2024-07-07 08:10:53.668437',4,4,'Ward Cunningham Đi thẳng từ con đường lập trình, Lập trình viên thực dụng vượt qua sự chuyên môn hóa và kỹ thuật ngày càng tăng của việc phát triển phần mềm hiện đại để kiểm tra quy trình cốt lõi--nhận yêu cầu và tạo ra mã có thể hoạt động, có thể bảo trì làm hài lòng người dùng. Nó bao gồm các chủ đề từ trách nhiệm cá nhân và phát triển nghề nghiệp đến các kỹ thuật kiến ​​trúc để giữ cho mã của bạn linh hoạt, dễ thích ứng và tái sử dụng. Hãy đọc cuốn sách này và bạn sẽ học cách Chống lại sự hư hỏng của phần mềm; Tránh bẫy sao chép kiến ​​thức; Viết mã linh hoạt, năng động và dễ thích ứng; Tránh lập trình một cách ngẫu nhiên; Chống đạn cho mã của bạn bằng các hợp đồng, xác nhận và ngoại lệ; Nắm bắt yêu cầu thực tế; Kiểm tra một cách tàn nhẫn và hiệu quả; Làm hài lòng người dùng của bạn; Xây dựng đội ngũ lập trình viên thực dụng; và Làm cho sự phát triển của bạn chính xác hơn bằng tự động hóa. Được viết dưới dạng một loạt các phần khép kín và chứa đầy những giai thoại thú vị, những ví dụ đáng suy ngẫm và những so sánh thú vị, Lập trình viên thực dụng minh họa những phương pháp hay nhất và những cạm bẫy lớn của nhiều khía cạnh khác nhau của phát triển phần mềm. Cho dù bạn là một lập trình viên mới, một chương trình có kinh nghiệm.'),(29,'1492051721','2024-07-06 15:23:08.111691','1720317438788.jpg','English',50000.00,2020,'O\'Reilly Media','ACTIVE','Learning React: Modern Patterns for Developing React Apps ',307,'2024-07-07 08:57:18.795734',4,4,'Nếu bạn muốn tìm hiểu cách xây dựng các ứng dụng React hiệu quả thì đây là cuốn sách dành cho bạn. Lý tưởng cho các nhà phát triển web và kỹ sư phần mềm hiểu cách JavaScript, CSS và HTML hoạt động trong trình duyệt, phiên bản cập nhật này cung cấp các phương pháp và mẫu tốt nhất để viết mã React hiện đại. Không cần có kiến ​​thức trước về React hoặc JavaScript chức năng.\n\nVới lộ trình học tập của mình, các tác giả Alex Banks và Eve Porcello chỉ cho bạn cách tạo giao diện người dùng có thể hiển thị khéo léo các thay đổi mà không cần tải lại trang trên các trang web dựa trên dữ liệu, quy mô lớn. Bạn cũng sẽ khám phá cách làm việc với lập trình chức năng và các tính năng ECMAScript mới nhất. Sau khi tìm hiểu cách xây dựng các thành phần React bằng hướng dẫn thực hành này, bạn sẽ hiểu React có thể hữu ích như thế nào trong tổ chức của bạn.\n\nHiểu các khái niệm lập trình chức năng chính bằng JavaScript Xem chi tiết để tìm hiểu cách React chạy trong trình duyệt Tạo các lớp trình bày ứng dụng với các thành phần React Quản lý dữ liệu và giảm thời gian bạn gỡ lỗi ứng dụng Kết hợp React Hooks để quản lý trạng thái và tìm nạp dữ liệu Sử dụng giải pháp định tuyến cho các tính năng của ứng dụng một trang Tìm hiểu cách cấu trúc các ứng dụng React có lưu ý đến máy chủ'),(30,'9781720043997','2024-07-06 15:31:16.450543','1720254678390.jpg','English',69000.00,2018,'','AVAILABLE','The Road to React: Your journey to master plain yet pragmatic React.js',286,'2024-07-06 15:31:18.397332',4,4,'LAST UPDATE: 5. February 2024\n\nThe Road to React: The React.js with Hooks in JavaScript Book (2024 Edition) - is a comprehensive and pragmatic yet concise React 18 with Hooks (+ opt-in TypeScript) book. Purchase of this book includes free online access to the always up-to-date digital book.\n\nWhat you will learn.\n\n\"The Road to React\" made its debut in 2016, and since then, I\'ve almost rewritten it annually. This book teaches the core principles of React, guiding you through building a practical application in pure React without complex tooling. The book covers everything from setting up the project to deploying it on a server. Each chapter includes additional recommended reading and exercises. By the end, you\'ll have the skills to develop your own React applications.\n\nIn \"The Road to React,\" I establish a solid foundation before delving into the broader React ecosystem. The book clarifies general concepts, patterns, and best practices for real-world React applications. Ultimately, you\'ll learn to construct a React application from scratch, incorporating features such as pagination, client-side and server-side searching, and advanced UI interactions like sorting.'),(31,'0739048120','2024-07-06 15:38:41.130331','1720255123128.jpg','English',50000.00,1995,'Alfred Music','AVAILABLE','Suzuki Violin School',36,'2024-07-06 15:38:43.130411',4,4,'Dạy violin với Trường dạy violin Suzuki nổi tiếng. Phương pháp Suzuki(R) về Giáo dục Tài năng dựa trên quan điểm của Shinichi Suzuki rằng mọi đứa trẻ đều sinh ra đã có năng khiếu, và con người là sản phẩm của môi trường. Theo Shinichi Suzuki, một nghệ sĩ violin và giáo viên nổi tiếng thế giới, niềm vui lớn nhất mà người lớn có thể biết đến là phát triển tiềm năng của trẻ em để trẻ có thể thể hiện tất cả những gì hài hòa và tốt nhất ở con người. Học viên được dạy theo phương pháp \"tiếng mẹ đẻ\". Mỗi bộ sách dành cho một nhạc cụ cụ thể trong Phương pháp Suzuki được coi là trường dạy nhạc Suzuki, chẳng hạn như Trường dạy vĩ cầm Suzuki. Các bài học Suzuki thường được cung cấp trong bối cảnh phòng thu riêng với các bài học nhóm bổ sung. Học viên nghe các bản ghi âm và làm việc với giáo viên dạy vĩ cầm Suzuki của mình để phát triển tiềm năng của mình với tư cách là một nhạc sĩ và một con người.\n\nSách Suzuki này là một phần không thể thiếu trong các bài học vĩ cầm Suzuki. Phiên bản sửa đổi này của Trường dạy vĩ cầm Suzuki, Tập 2 có:\n* Biên tập lại các bản nhạc, bao gồm cả cách kéo vĩ và bấm ngón\n* 16 trang bổ sung\n* Các bài tập bổ sung, một số bài của Shinichi Suzuki, cùng với những hiểu biết sâu sắc và gợi ý bổ sung cho giáo viên\n* Văn bản bằng tiếng Anh, tiếng Pháp, tiếng Đức và tiếng Tây Ban Nha\n* Hướng dẫn ký hiệu âm nhạc\n* Vị trí cần đàn.\n'),(32,'0874874394','2024-07-06 15:41:37.826838','1720314575325.jpg','English',59000.00,1995,'Alfred Music','ACTIVE','I Can Read Music',108,'2024-07-07 08:09:35.327606',4,4,'Những bài tập dễ đọc, tiến bộ này của Joanne Martin phát triển kỹ năng đọc của học sinh theo từng giai đoạn, với nhiều lần lặp lại ở mỗi giai đoạn. I Can Read Music được thiết kế như một cuốn sách đọc nốt nhạc đầu tiên dành cho học viên chơi nhạc cụ dây đã học chơi bằng cách sử dụng phương pháp tiếp cận âm thanh như Phương pháp Suzuki (R) hoặc dành cho những học viên được dạy theo kiểu truyền thống cần thực hành đọc nốt nhạc thêm. Trình bày các ý tưởng mới của nó đủ rõ ràng để trẻ nhỏ và cha mẹ chúng có thể sử dụng hàng ngày ở nhà, giáo viên sẽ kiểm tra tiến độ mỗi tuần hoặc hai tuần.'),(34,'9780143129257','2024-07-07 08:07:39.642679','1720314461626.jpg','English',50000.00,2016,'TarcherPerigee','ACTIVE','The Artist\'s Way: 30th Anniversary Edition',272,'2024-07-07 08:07:41.635627',4,4,'Kể từ lần xuất bản đầu tiên, hiện tượng Con đường nghệ sĩ đã truyền cảm hứng cho thiên tài Elizabeth Gilbert và hàng triệu độc giả bắt tay vào hành trình sáng tạo và tìm ra mối liên hệ sâu sắc hơn với quá trình và mục đích. Cách tiếp cận mới lạ của Julia Cameron hướng dẫn người đọc khám phá các lĩnh vực có vấn đề và các điểm áp lực có thể hạn chế dòng sáng tạo của họ, đồng thời đưa ra các kỹ thuật để giải phóng bất kỳ lĩnh vực nào mà họ có thể bị mắc kẹt, mở ra cơ hội phát triển bản thân và khám phá bản thân.');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_author` (
  `book_id` int NOT NULL,
  `author_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`author_id`),
  KEY `FKbjqhp85wjv8vpr0beygh6jsgo` (`author_id`),
  CONSTRAINT `FKbjqhp85wjv8vpr0beygh6jsgo` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `FKhwgu59n9o80xv75plf9ggj7xn` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_author`
--

LOCK TABLES `book_author` WRITE;
/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` VALUES (28,1),(28,2),(29,4),(29,5),(30,6),(31,7),(32,7),(34,9);
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_category` (
  `book_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`book_id`,`category_id`),
  KEY `FKam8llderp40mvbbwceqpu6l2s` (`category_id`),
  CONSTRAINT `FKam8llderp40mvbbwceqpu6l2s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKnyegcbpvce2mnmg26h0i856fd` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` VALUES (28,10),(29,10),(31,11),(32,11),(34,12);
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_copy`
--

DROP TABLE IF EXISTS `book_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_copy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bar_code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varbinary(255) DEFAULT NULL,
  `status` enum('ACTIVE','AVAILABLE','BORROWED','DAMAGED','INACTIVE','LOST','OUT_OF_STOCK') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varbinary(255) DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKq8bucs9fxg4fyc077ym5w4i0j` (`bar_code`),
  KEY `FKpqftp65hd66ae8wsx7pp2cxcs` (`book_id`),
  CONSTRAINT `FKpqftp65hd66ae8wsx7pp2cxcs` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_copy`
--

LOCK TABLES `book_copy` WRITE;
/*!40000 ALTER TABLE `book_copy` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4gee1gl2pvbutkm3b6l32mxg6` (`created_by`),
  KEY `FKjho6rkc8456ho2u9myv172can` (`updated_by`),
  KEY `FK2y94svpmqttx80mshyny85wqr` (`parent_id`),
  CONSTRAINT `FK2y94svpmqttx80mshyny85wqr` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK4gee1gl2pvbutkm3b6l32mxg6` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjho6rkc8456ho2u9myv172can` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (6,'2024-07-05 01:15:14.952249','Máy tính & Công nghệ','2024-07-05 01:15:14.952249',4,4,NULL),(8,'2024-07-05 01:25:55.520292','Khoa học máy tính','2024-07-05 02:15:33.501688',4,4,6),(9,'2024-07-05 01:44:57.877467','Nghệ thuật & Nhiếp ảnh','2024-07-05 01:44:57.877467',4,4,NULL),(10,'2024-07-05 02:14:27.567439','Lập trình','2024-07-05 02:19:08.166791',4,4,6),(11,'2024-07-06 15:32:57.267624','Âm nhạc','2024-07-06 15:32:57.267624',4,4,9),(12,'2024-07-06 18:59:18.718113','Lịch sử & Phê bình','2024-07-06 18:59:18.718113',4,4,9);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `borrow_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `return_at` datetime(6) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `book_copy_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhq4lfr6shkjcbqvn0if6sqtwf` (`book_copy_id`),
  KEY `FKaxlogaf6ygbchsximkhuey6dj` (`member_id`),
  CONSTRAINT `FKaxlogaf6ygbchsximkhuey6dj` FOREIGN KEY (`member_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhq4lfr6shkjcbqvn0if6sqtwf` FOREIGN KEY (`book_copy_id`) REFERENCES `book_copy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_subscription`
--

DROP TABLE IF EXISTS `member_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_subscription` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `loan_duration` int DEFAULT NULL,
  `max_book` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `renew_duration` int DEFAULT NULL,
  `renew_limit` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKobknjemi425wno3cs79lav76g` (`user_id`),
  KEY `FK5jpwu6a3adqebekouebq5gwvt` (`updated_by`),
  CONSTRAINT `FK5jpwu6a3adqebekouebq5gwvt` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKobknjemi425wno3cs79lav76g` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_subscription`
--

LOCK TABLES `member_subscription` WRITE;
/*!40000 ALTER TABLE `member_subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `checksum` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `process` enum('DONE','INPROCESS') DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `payment` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK13b9opq1w4d0xkmk60yu439d7` (`payment`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKollpf727aq6g58cl8wve5lhxb` FOREIGN KEY (`payment`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_time` datetime(6) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `status_payment` enum('FAIL','PENDING','SUCCESS') DEFAULT NULL,
  `payment_gateway` enum('MOMO','PAYPAL','VN_PAY') DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4spfnm9si9dowsatcqs5or42i` (`user_id`),
  CONSTRAINT `FK4spfnm9si9dowsatcqs5or42i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renewals`
--

DROP TABLE IF EXISTS `renewals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renewals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `new_due_date` datetime(6) DEFAULT NULL,
  `renewed_at` datetime(6) DEFAULT NULL,
  `return_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `loan_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrd3ot4neh52uuhsx0u8nrnhtu` (`loan_id`),
  CONSTRAINT `FKrd3ot4neh52uuhsx0u8nrnhtu` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renewals`
--

LOCK TABLES `renewals` WRITE;
/*!40000 ALTER TABLE `renewals` DISABLE KEYS */;
/*!40000 ALTER TABLE `renewals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK70yrt09r4r54tcgkrwbeqenbs` (`book_id`),
  KEY `FKuldpqt6am6kr836rn7ghcgtu` (`member_id`),
  CONSTRAINT `FK70yrt09r4r54tcgkrwbeqenbs` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FKuldpqt6am6kr836rn7ghcgtu` FOREIGN KEY (`member_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample_book`
--

DROP TABLE IF EXISTS `sample_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sample_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `sample_book_image` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr77q5tp962yk8tpdlhmhmtahk` (`book_id`),
  CONSTRAINT `FKr77q5tp962yk8tpdlhmhmtahk` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample_book`
--

LOCK TABLES `sample_book` WRITE;
/*!40000 ALTER TABLE `sample_book` DISABLE KEYS */;
INSERT INTO `sample_book` VALUES (36,'2024-07-06 15:31:18.436975','1720254678433.jpg','2024-07-06 15:31:18.436975',30),(37,'2024-07-06 15:31:18.438975','1720254678432.jpg','2024-07-06 15:31:18.438975',30),(38,'2024-07-06 15:38:43.162817','1720255123160.jpg','2024-07-06 15:38:43.162817',31),(39,'2024-07-06 15:38:43.163819','1720255123159.png','2024-07-06 15:38:43.163819',31),(52,'2024-07-07 08:07:41.674343','1720314461670.jpg','2024-07-07 08:07:41.674343',34),(53,'2024-07-07 08:07:41.676336','1720314461671.jpg','2024-07-07 08:07:41.676336',34),(54,'2024-07-07 08:09:35.357141','1720314575354.png','2024-07-07 08:09:35.357141',32),(55,'2024-07-07 08:09:35.359141','1720314575355.jpg','2024-07-07 08:09:35.359141',32),(56,'2024-07-07 08:10:53.715255','1720314653714.jpg','2024-07-07 08:10:53.715255',28),(57,'2024-07-07 08:10:53.717264','1720314653712.png','2024-07-07 08:10:53.717264',28),(58,'2024-07-07 08:10:53.719510','1720314653713.png','2024-07-07 08:10:53.719510',28),(59,'2024-07-07 08:57:18.855900','1720317438851.jpg','2024-07-07 08:57:18.855900',29),(60,'2024-07-07 08:57:18.869986','1720317438850.jpg','2024-07-07 08:57:18.869986',29);
/*!40000 ALTER TABLE `sample_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `loan_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkp2yyh868utly7r7ntwarpn6a` (`loan_id`),
  CONSTRAINT `FKkp2yyh868utly7r7ntwarpn6a` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','LIBRARIAN','MEMBER') DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `verified` bit(1) DEFAULT NULL,
  `member_subscription_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3qy291j8kqb4uqmodb7kykrxo` (`member_subscription_id`),
  CONSTRAINT `FK3qy291j8kqb4uqmodb7kykrxo` FOREIGN KEY (`member_subscription_id`) REFERENCES `member_subscription` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2024-06-19 15:12:00.514000','diennvhe171038@fpt.edu.vn','Nguyễn Văn Diên','$2a$10$9vURIbUB6pDFDMQWBSiEJew7zm6SNRDeUD8z3PQw7zWJqObLCZNB2',NULL,'ADMIN','ACTIVE','2024-06-19 15:13:49.754000',_binary '',NULL),(4,'2024-06-19 22:33:40.917000','hphuca12004@gmail.com','Phuc Nguyen','$2a$10$PaY.yBvnkaCphQ1/PCCqtuBIY5TtKQ9bdSI4cergPNmnzcnhgsCtm','0283549325','LIBRARIAN','ACTIVE','2024-06-20 08:52:03.682000',_binary '',NULL),(5,'2024-06-20 22:54:10.321000','ndien9203@gmail.com','Nguyen Dien','$2a$10$Y.DvWp3mKXOLVg36CRUld.KRT69.fYb/BXIGsW0uOXefTBivkf4V.','0122345643','MEMBER','ACTIVE','2024-06-21 15:10:37.972000',_binary '',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-07 12:44:19
