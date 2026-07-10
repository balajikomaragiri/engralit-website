-- This runs automatically on startup and inserts the real Engralit course
-- Safe to run multiple times because of the IF NOT EXISTS check pattern below

INSERT INTO courses (id, title, tagline, price, duration_days, faculty_name, mode, validity, contact_number, thumbnail_url)
SELECT 1, 'Target NET/SET English (Paper-II)', 'Complete Preparation for UGC NET & SET Exams',
       4500.00, 60, 'M. Balaji', '100% Online, Recorded Classes', 'Till exam', '7799691771', '/images/course-banner.jpg'
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE id = 1);

-- 10 Syllabus Units
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 1, 1, 'Drama', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 1);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 2, 2, 'Poetry', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 2);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 3, 3, 'Fiction, Short Story', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 3);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 4, 4, 'Non-Fictional Prose', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 4);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 5, 5, 'Language: Basic Concepts, Theories and Pedagogy', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 5);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 6, 6, 'English in India: History, Evolution and Futures', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 6);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 7, 7, 'Cultural Studies', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 7);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 8, 8, 'Literary Criticism', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 8);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 9, 9, 'Literary Theory Post World War II', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 9);
INSERT INTO syllabus_units (id, unit_no, unit_title, course_id)
SELECT 10, 10, 'Research Methods and Materials in English', 1 WHERE NOT EXISTS (SELECT 1 FROM syllabus_units WHERE id = 10);

-- 6 Course Highlights (icon classes are from Bootstrap Icons - already linked in the HTML)
INSERT INTO course_features (id, feature_text, icon_class, course_id)
SELECT 1, 'Complete PDF Study Material', 'bi-file-earmark-text', 1 WHERE NOT EXISTS (SELECT 1 FROM course_features WHERE id = 1);
INSERT INTO course_features (id, feature_text, icon_class, course_id)
SELECT 2, 'Recorded Video Classes', 'bi-camera-video', 1 WHERE NOT EXISTS (SELECT 1 FROM course_features WHERE id = 2);
INSERT INTO course_features (id, feature_text, icon_class, course_id)
SELECT 3, 'Previous Year Questions', 'bi-question-circle', 1 WHERE NOT EXISTS (SELECT 1 FROM course_features WHERE id = 3);
INSERT INTO course_features (id, feature_text, icon_class, course_id)
SELECT 4, 'Topic-wise MCQs', 'bi-list-check', 1 WHERE NOT EXISTS (SELECT 1 FROM course_features WHERE id = 4);
INSERT INTO course_features (id, feature_text, icon_class, course_id)
SELECT 5, 'Mock Tests & Analysis', 'bi-graph-up-arrow', 1 WHERE NOT EXISTS (SELECT 1 FROM course_features WHERE id = 5);
INSERT INTO course_features (id, feature_text, icon_class, course_id)
SELECT 6, 'Doubt Clarification via WhatsApp', 'bi-whatsapp', 1 WHERE NOT EXISTS (SELECT 1 FROM course_features WHERE id = 6);
