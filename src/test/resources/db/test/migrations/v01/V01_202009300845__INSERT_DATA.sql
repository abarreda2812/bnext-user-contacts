INSERT INTO BNEXT_USER (id, name, last_name, phone) VALUES (USER_ID_GENERATOR.NEXTVAL, 'Alejandro', 'Barreda', '675926967');
INSERT INTO BNEXT_USER (id, name, last_name, phone) VALUES (USER_ID_GENERATOR.NEXTVAL, 'Alejandro', 'Barreda2', '675926969');
INSERT INTO BNEXT_USER_CONTACT (id, contact_name, phone) VALUES (USER_CONTACT_ID_GENERATOR.NEXTVAL, 'Roberto Perez', '675926968');
INSERT INTO BNEXT_USER_USER_CONTACT (user_id, user_contact_id) VALUES (1, 1);
INSERT INTO BNEXT_USER_USER_CONTACT (user_id, user_contact_id) VALUES (2, 1);