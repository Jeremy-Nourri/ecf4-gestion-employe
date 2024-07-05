USE gestionemploye;

INSERT INTO department (id, name) VALUES
(1, 'ingénierie logiciel'),
(2, 'ressources humaines'),
(3, 'marketing'),
(4, 'finance');

INSERT INTO position (id, title) VALUES
(1, 'développeur back'),
(2, 'développeur front'),
(3, 'architecte logiciel'),
(4, 'chef de projet'),
(5, 'analyste QA'),
(6, 'scrum master'),
(7, 'responsable recrutement'),
(8, 'gestionnaire paie'),
(9, 'responsable marketing'),
(10, 'spécialiste SEO'),
(11, 'analyste financier'),
(12, 'contrôleur de gestion');

INSERT INTO employee (id, firstName, lastName, email, phone, department_id, position_id) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '1234567890', 1, 1),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', 1, 2),
(3, 'Albert', 'Brown', 'albert.brown@example.com', '1122334455', 1, 3),
(4, 'Maria', 'Garcia', 'maria.garcia@example.com', '6677889900', 1, 4),
(5, 'David', 'Wilson', 'david.wilson@example.com', '2233445566', 1, 5),
(6, 'Laura', 'Martinez', 'laura.martinez@example.com', '3344556677', 1, 6),
(7, 'Emily', 'Clark', 'emily.clark@example.com', '4455667788', 2, 7),
(8, 'Michael', 'Johnson', 'michael.johnson@example.com', '9988776655', 2, 8),
(9, 'Sarah', 'Williams', 'sarah.williams@example.com', '5566778899', 3, 9),
(10, 'James', 'Davis', 'james.davis@example.com', '3344221155', 3, 10),
(11, 'Olivia', 'Rodriguez', 'olivia.rodriguez@example.com', '7788996655', 4, 11),
(12, 'Daniel', 'Martinez', 'daniel.martinez@example.com', '6655443322', 4, 12);