-- INSERT USER DETAILS
INSERT INTO user_details VALUES (1, '5th Avenue 255', 'New York');
INSERT INTO user_details VALUES (2, 'Japanese Street', 'Tokyo');


-- INSERT USERS
INSERT INTO users VALUES (1, 'user', 'Eric', NULL, 'Hanks', '$2a$10$XO8w6gPq2Rysd1PsAJCrBuLEozFylseNmCA/lD5X7SlkeoF816IBG', 'user@webstore.com', 1, 1);
INSERT INTO users VALUES (2, 'admin', 'David', 'Ryan', 'Stewart', '$2a$10$ruJG.3IKT6bFpDrVUGj.zuVoZBR1KuGTQ/d78A8hKlBKCCRWXe0lW', 'admin@webstore.com', 2, 1);


-- INSERT AUTHORITIES
INSERT INTO authorities VALUES (1, 'user', 'ROLE_USER');
INSERT INTO authorities VALUES (2, 'admin', 'ROLE_USER');
INSERT INTO authorities VALUES (3, 'admin', 'ROLE_ADMIN');


-- INSERT BOOK DETAILS
INSERT INTO book_details VALUES (1, 368, 'Winnie-the-Pooh (1926) is the first volume of stories about Winnie-the-Pooh, by A. A. Milne. It is followed by The House at Pooh Corner. The book focuses on the adventures of a teddy bear called Winnie-the-Pooh and his friends Piglet, a small toy pig; Eeyore, a toy donkey; Owl, a live owl; and Rabbit, a live rabbit. The characters of Kanga, a toy kangaroo, and her son Roo are introduced later in the book.');
INSERT INTO book_details VALUES (2, 180, 'The House at Pooh Corner (1928) is the second volume of stories about Winnie-the-Pooh, written by A. A. Milne and illustrated by E. H. Shepard. It is notable for the introduction of the character Tigger.');
INSERT INTO book_details VALUES (3, 624, 'Spring in Action, Fourth Edition is a hands-on guide to the Spring Framework, updated for version 4. It covers the latest features, tools, and practices including Spring MVC, REST, Security, Web Flow, and more. You will move between short snippets and an ongoing example as you learn to build simple and efficient J2EE applications. Author Craig Walls has a special knack for crisp and entertaining examples that zoom in on the features and techniques you really need.');
INSERT INTO book_details VALUES (4, 560, 'Spring in Practice shows you how to tackle the challenges you face when you build Spring-based applications. The book empowers software developers to solve concrete business problems by mapping application-level issues to Spring-centric solutions. It diverges from other cookbooks because it presents the background you need to understand the domain in which a solution applies before it offers the specific steps to solve the problem.');
INSERT INTO book_details VALUES (5, 464, 'Even bad code can function. But if code isn’t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn’t have to be that way. Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship . Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code “on the fly” into a book that will instill within you the values of a software craftsman and make you a better programmer—but only if you work at it.');
INSERT INTO book_details VALUES (6, 336, 'Nineteen Eighty-Four, often published as 1984, is a dystopian novel published in 1949 by English author George Orwell.[2][3] The novel is set in Airstrip One, formerly Great Britain, a province of the superstate Oceania, whose residents are victims of perpetual war, omnipresent government surveillance and public manipulation. Oceania’s political ideology, euphemistically named English Socialism (shortened to \"Ingsoc\" in Newspeak, the government’s invented language that will replace English or Oldspeak) is enforced by the privileged, elite Inner Party. Via the \"Thought Police\", the Inner Party persecutes individualism and independent thinking, which are regarded as \"thoughtcrimes\".');
INSERT INTO book_details VALUES (7, 112, 'Animal Farm is an allegorical novella by George Orwell, first published in England on 17 August 1945. According to Orwell, the book reflects events leading up to the Russian Revolution of 1917 and then on into the Stalinist era of the Soviet Union.[1] Orwell, a democratic socialist,[2] was a critic of Joseph Stalin and hostile to Moscow-directed Stalinism, an attitude that was critically shaped by his experiences during the Spanish Civil War.[3] The Soviet Union, he believed, had become a brutal dictatorship, built upon a cult of personality and enforced by a reign of terror. In a letter to Yvonne Davet, Orwell described Animal Farm as a satirical tale against Stalin (\"un conte satirique contre Staline\"),[4] and in his essay \"Why I Write\" (1946), wrote that Animal Farm was the first book in which he tried, with full consciousness of what he was doing, \"to fuse political purpose and artistic purpose into one whole\".');
INSERT INTO book_details VALUES (8, 448, 'The Godfather is a crime novel written by American author Mario Puzo. Originally published in 1969 by G. P. Putnam’s Sons, the novel details the story of a fictional Mafia family based in New York City (and Long Beach, New York), headed by Vito Corleone. The novel covers the years 1945 to 1955, and also provides the back story of Vito Corleone from early childhood to adulthood.');
INSERT INTO book_details VALUES (9, 142, 'In the spirit of Steve Jobs and Moneyball, Elon Musk is both an illuminating and authorized look at the extraordinary life of one of Silicon Valley’s most exciting, unpredictable, and ambitious entrepreneurs--a real-life Tony Stark--and a fascinating exploration of the renewal of American invention and its new \"makers.\" Elon Musk spotlights the technology and vision of Elon Musk, the renowned entrepreneur and innovator behind SpaceX, Tesla, and SolarCity, who sold one of his Internet companies, PayPal, for $1.5 billion. Ashlee Vance captures the full spectacle and arc of the genius’s life and work, from his tumultuous upbringing in South Africa and flight to the United States to his dramatic technical innovations and entrepreneurial pursuits.');
INSERT INTO book_details VALUES (10, 240, 'Follows two TDD projects from start to finish, illustrating techniques programmers can use to increase the quality of their work. The examples are followed by references to the featured TDD patterns and refactorings. This book emphasises on agile methods and fast development strategies.');
INSERT INTO book_details VALUES (11, 528, 'The Wolf of Wall Street is a memoir by former stockbroker and trader Jordan Belfort, first published in September 2007 by Bantam Books,[1][2] then adapted into a 2013 film of the same name (directed by Martin Scorsese and starring Leonardo DiCaprio as Belfort). Belfort’s autobiographical account was continued by Catching the Wolf of Wall Street, published in 2009.');
INSERT INTO book_details VALUES (12, 480, 'Catching the Wolf of Wall Street: More Incredible True Stories of Fortunes, Schemes, Parties, and Prison is the second non-fiction book by former stockbroker and trader Jordan Belfort. The text was initially published on February 24, 2009 by Bantam Books. The first book, The Wolf of Wall Street, explores his epic rise and fall in the financial world. The second memoir describes Belfort’s life and events after his arrest.[4] The sale of the rights to cinematize these two books is estimated to have earned Belfort some $2 million.');
INSERT INTO book_details VALUES (13, 570, 'Angels & Demons is a 2000 bestselling mystery-thriller novel written by American author Dan Brown and published by Pocket Books and then by Corgi Books. The novel introduces the character Robert Langdon, who recurs as the protagonist of Brown’s subsequent novels. Angels & Demons shares many stylistic literary elements with its sequels, such as conspiracies of secret societies, a single-day time frame, and the Catholic Church. Ancient history, architecture, and symbology are also heavily referenced throughout the book.');
INSERT INTO book_details VALUES (14, 455, 'The Da Vinci Code is the 2003 novel written by Dan Brown. It follows Harvard professor and symbologist Robert Langdon and the gifted French cryptologist Sophie Neveu as they investigate a murder in Paris’ Louvre Museum. They are stunned to discover bizarre riddles that lead them to a trail of clues hidden in the works of Leonardo da Vinci, seemingly left by the museum’s late curator, Jacques Saunière minutes before his death. Their race to discover the closely guarded secret held by Saunière uncovers a battle between the Priory of Sion and Opus Dei over the possibility of Jesus having been married to Mary Magdalene.');
INSERT INTO book_details VALUES (15, 400, 'Java Persistence with Hibernate, Second Edition is now available. An eBook of the previous edition is included at no additional cost when you buy the revised edition! Hibernate in Action carefully explains the concepts you need, then gets you going. It builds on a single example to show you how to use Hibernate in practice, how to deal with concurrency and transactions, how to efficiently retrieve objects and use caching. The authors created Hibernate and they field questions from the Hibernate community every day - they know how to make Hibernate sing. Knowledge and insight seep out of every pore of this book.');
INSERT INTO book_details VALUES (16, 416, 'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers. As in previous editions, each chapter of Effective Java, Third Edition, consists of several “items,” each presented in the form of a short, stand-alone essay that provides specific advice, insight into Java platform subtleties, and updated code examples. The comprehensive descriptions and explanations for each item illuminate what to do, what not to do, and why. The third edition covers language and library features added in Java 7, 8, and 9, including the functional programming constructs that were added to its object-oriented roots. Many new items have been added, including a chapter devoted to lambdas and streams.');
INSERT INTO book_details VALUES (17, 374, 'Pet Sematary StephenKingPetSematary.jpg First edition cover Author Stephen King Cover artist Linda Fennimore Country United States Language English Genre Horror Publisher Doubleday Publication date November 14, 1983 Media type Print (Hardcover) Pages 374 ISBN 978-0-385-18244-7 Pet Sematary is a 1983 horror novel by Stephen King, nominated for a World Fantasy Award for Best Novel in 1986, and adapted into a 1989 film of the same name. In November 2013, PS Publishing released Pet Sematary in a limited 30th Anniversary Edition.');
INSERT INTO book_details VALUES (18, 199, 'Carrie is a novel by American author Stephen King. It was his first published novel, released on April 5, 1974, with an approximate first print-run of 30,000 copies. Set primarily in the then-future year of 1979, it revolves around the eponymous Carrie White, a misfit and bullied high school girl who uses her newly discovered telekinetic powers to exact revenge on those who torment her. While in this process, she causes one of the worst local disasters the town has ever had. King has commented that he finds the work to be \"raw\" and \"with a surprising power to hurt and horrify.\" It is one of the most frequently banned books in United States schools. Much of the book uses newspaper clippings, magazine articles, letters, and excerpts from books to tell how Carrie destroyed the fictional town of Chamberlain, Maine while exacting revenge on her sadistic classmates and her own mother Margaret.');
	
	
-- INSERT BOOKS
INSERT INTO books VALUES (1, 'Winnie-the-Pooh', 1, 25.49, 60);
INSERT INTO books VALUES (2, 'The House at Pooh Corner', 2, 6.99, 75);
INSERT INTO books VALUES (3, 'Spring in Action, Fourth Edition', 3, 49.99, 75);
INSERT INTO books VALUES (4, 'Spring in Practice', 4, 44.99, 75);
INSERT INTO books VALUES (5, 'Clean Code: A Handbook of Agile Software Craftsmanship', 5, 41.53, 75);
INSERT INTO books VALUES (6, 'Nineteen Eighty-Four 1984', 6, 9.99, 75);
INSERT INTO books VALUES (7, 'Animal Farm', 7, 6.99, 75);
INSERT INTO books VALUES (8, 'The Godfather', 8, 19.95, 75);
INSERT INTO books VALUES (9, 'Tesla, SpaceX, and the Quest for a Fantastic Future', 9, 39.95, 75);
INSERT INTO books VALUES (10, 'Test Driven Development', 10, 24.83, 75);
INSERT INTO books VALUES (11, 'The Wolf of Wall Street', 11, 39.95, 75);
INSERT INTO books VALUES (12, 'Catching the Wolf of Wall Street', 12, 39.95, 75);
INSERT INTO books VALUES (13, 'Angels & Demons', 13, 39.95, 75);
INSERT INTO books VALUES (14, 'The Da Vinci Code', 14, 39.95, 75);
INSERT INTO books VALUES (15, 'Hibernate in Action', 15, 49.95, 75);
INSERT INTO books VALUES (16, 'Effective Java (3rd Edition)', 16, 47.17, 75);
INSERT INTO books VALUES (17, 'Pet Sematary', 17, 29.95, 75);
INSERT INTO books VALUES (18, 'Carrie', 18, 12.49, 75);


-- INSERT AUTHORS
INSERT INTO authors VALUES (1, 'Alexander', 'Milne');
INSERT INTO authors VALUES (2, 'Craig', 'Walls');
INSERT INTO authors VALUES (3, 'Willie', 'Wheeler');
INSERT INTO authors VALUES (4, 'Joshua', 'White');
INSERT INTO authors VALUES (5, 'Robert', 'Martin');
INSERT INTO authors VALUES (6, 'George', 'Orwell');
INSERT INTO authors VALUES (7, 'Mario', 'Puzo');
INSERT INTO authors VALUES (8, 'Elon', 'Musk');
INSERT INTO authors VALUES (9, 'Kent', 'Beck');
INSERT INTO authors VALUES (10, 'Jordan', 'Belfort');
INSERT INTO authors VALUES (11, 'Dan', 'Brown');
INSERT INTO authors VALUES (12, 'Christian', 'Bauer');
INSERT INTO authors VALUES (13, 'Gavin', 'King');
INSERT INTO authors VALUES (14, 'Joshua', 'Bloch');
INSERT INTO authors VALUES (15, 'Stephen', 'King');


-- INSERT BOOK-AUTHORS <MANY TO MANY>
INSERT INTO books_authors VALUES (1, 1);
INSERT INTO books_authors VALUES (2, 1);
INSERT INTO books_authors VALUES (3, 2);
INSERT INTO books_authors VALUES (4, 3);
INSERT INTO books_authors VALUES (4, 4);
INSERT INTO books_authors VALUES (5, 5);
INSERT INTO books_authors VALUES (6, 6);
INSERT INTO books_authors VALUES (7, 6);
INSERT INTO books_authors VALUES (8, 7);
INSERT INTO books_authors VALUES (9, 8);
INSERT INTO books_authors VALUES (10, 9);
INSERT INTO books_authors VALUES (11, 10);
INSERT INTO books_authors VALUES (12, 10);
INSERT INTO books_authors VALUES (13, 11);
INSERT INTO books_authors VALUES (14, 11);
INSERT INTO books_authors VALUES (15, 12);
INSERT INTO books_authors VALUES (15, 13);
INSERT INTO books_authors VALUES (16, 14);
INSERT INTO books_authors VALUES (17, 15);
INSERT INTO books_authors VALUES (18, 15);
