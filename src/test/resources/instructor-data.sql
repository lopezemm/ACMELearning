INSERT INTO instructors ( instructor_join_date, instructor_last_name, instructor_name)
VALUES
('2021-01-01', 'hernandez', 'emm');
INSERT INTO courses ( course_name, create_date, is_started, instructor_id)
VALUES
('java', '2021-01-01', 'N', 1);
INSERT INTO students ( student_age, student_last_name, student_name)
VALUES
(18, 'perez', 'test');
INSERT INTO course_student ( course_id, student_id)
VALUES
(1,1);
--INSERT INTO helper ( city, country, name)
--VALUES
--('morelia', 'mexico', 'helper');
--INSERT INTO dish_helper (dish_id, helper_id)
--VALUES
--(1,1)