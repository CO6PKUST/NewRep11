--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Debian 16.1-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: type_entity; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.type_entity AS ENUM (
    'post',
    'messages',
    'remark'
);


ALTER TYPE public.type_entity OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: data_obj; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.data_obj (
    data_obj_id integer NOT NULL,
    data_obj_name character varying(256) NOT NULL,
    ext character varying(8) NOT NULL,
    reference text NOT NULL
);


ALTER TABLE public.data_obj OWNER TO postgres;

--
-- Name: dataobj_dataobj_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.data_obj ALTER COLUMN data_obj_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dataobj_dataobj_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: education; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.education (
    person_id integer NOT NULL,
    university character varying(256),
    university_title character varying(128)
);


ALTER TABLE public.education OWNER TO postgres;

--
-- Name: favorites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favorites (
    person_id integer NOT NULL,
    post_id integer NOT NULL
);


ALTER TABLE public.favorites OWNER TO postgres;

--
-- Name: friends; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friends (
    person_id integer NOT NULL,
    friend_id integer NOT NULL
);


ALTER TABLE public.friends OWNER TO postgres;

--
-- Name: has_data_obj; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.has_data_obj (
    entity public.type_entity NOT NULL,
    data_obj_id integer NOT NULL,
    entity_id integer NOT NULL
);


ALTER TABLE public.has_data_obj OWNER TO postgres;

--
-- Name: hobby_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hobby_table (
    hobby_id integer NOT NULL,
    hobby_name character varying(128) NOT NULL
);


ALTER TABLE public.hobby_table OWNER TO postgres;

--
-- Name: hobbytable_hobby_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.hobby_table ALTER COLUMN hobby_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hobbytable_hobby_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: job; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.job (
    person_id integer NOT NULL,
    job character varying(256),
    job_title character varying(128)
);


ALTER TABLE public.job OWNER TO postgres;

--
-- Name: language_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.language_table (
    language_id integer NOT NULL,
    language_name character varying(32) NOT NULL
);


ALTER TABLE public.language_table OWNER TO postgres;

--
-- Name: languagetable_language_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.language_table ALTER COLUMN language_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.languagetable_language_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    from_person_id integer NOT NULL,
    to_person_id integer NOT NULL,
    message_time timestamp without time zone NOT NULL,
    description text NOT NULL,
    messages_id integer NOT NULL
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.messages ALTER COLUMN messages_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.messages_messages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    person_id integer NOT NULL,
    first_name character varying(128),
    second_name character varying(128),
    email character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    nickname character varying(256) NOT NULL,
    enabled boolean NOT NULL
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_has_hobby; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_has_hobby (
    person_id integer NOT NULL,
    hobby_id integer NOT NULL
);


ALTER TABLE public.person_has_hobby OWNER TO postgres;

--
-- Name: person_has_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_has_role (
    person_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.person_has_role OWNER TO postgres;

--
-- Name: person_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN person_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.person_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: person_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_profile (
    person_id integer NOT NULL,
    sex boolean,
    about_me text,
    registration_date_time date NOT NULL,
    person_pic integer,
    person_cover integer,
    number_bank_card character varying(32),
    birthday date,
    last_online timestamp without time zone NOT NULL
);


ALTER TABLE public.person_profile OWNER TO postgres;

--
-- Name: person_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_role (
    role_id integer NOT NULL,
    role_name character varying(128) NOT NULL
);


ALTER TABLE public.person_role OWNER TO postgres;

--
-- Name: person_speak_language; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_speak_language (
    person_id integer NOT NULL,
    language_id integer NOT NULL
);


ALTER TABLE public.person_speak_language OWNER TO postgres;

--
-- Name: personrole_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person_role ALTER COLUMN role_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.personrole_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post (
    post_id integer NOT NULL,
    person_id integer NOT NULL,
    description text,
    placement_time timestamp without time zone NOT NULL
);


ALTER TABLE public.post OWNER TO postgres;

--
-- Name: post_post_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.post ALTER COLUMN post_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.post_post_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: remark; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.remark (
    remark_id integer NOT NULL,
    person_id integer NOT NULL,
    description text NOT NULL,
    parent_id integer,
    post_id integer NOT NULL
);


ALTER TABLE public.remark OWNER TO postgres;

--
-- Name: remark_remark_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.remark ALTER COLUMN remark_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.remark_remark_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: data_obj; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.data_obj (data_obj_id, data_obj_name, ext, reference) FROM stdin;
\.


--
-- Data for Name: education; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.education (person_id, university, university_title) FROM stdin;
\.


--
-- Data for Name: favorites; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.favorites (person_id, post_id) FROM stdin;
\.


--
-- Data for Name: friends; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.friends (person_id, friend_id) FROM stdin;
\.


--
-- Data for Name: has_data_obj; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.has_data_obj (entity, data_obj_id, entity_id) FROM stdin;
\.


--
-- Data for Name: hobby_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.hobby_table (hobby_id, hobby_name) FROM stdin;
\.


--
-- Data for Name: job; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.job (person_id, job, job_title) FROM stdin;
\.


--
-- Data for Name: language_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.language_table (language_id, language_name) FROM stdin;
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (from_person_id, to_person_id, message_time, description, messages_id) FROM stdin;
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (person_id, first_name, second_name, email, password, nickname, enabled) FROM stdin;
2	Petr	Petrov	petr@mail.ru	$2b$12$HB.rQsZuubVcamZNxI5cbOgUN6eIlGzowdGd.cjzexlCpMXNO/q5K	admin	t
1	Ivan	Ivanov	ivan@mail.ru	$2b$12$HB.rQsZuubVcamZNxI5cbO.5xOLGK6jfH/hoD1SbeQbxaLLrZbMye	user	t
3	\N	\N	bob@mail.ru	$2a$10$PF0a5vYib7eh1lXQh5Ll5e.Mf6L8QKOHAik40hGoHZOXEfc2N/CqS	bob	t
4	\N	\N	bob@mail.ru	$2a$10$vqXuUUYNwyfPY5I55qBXj.JCGRBfY7bggfa6WJ1wjvHZ.rzLCpojC	mary	t
5	\N	\N	newmail@mail.ru	$2a$10$mP4x0J/qG8LNxxX4YF05DejyA0sE7jn2Ai9RVyfAgFg5O2Z/HN7Ci	usernull	t
6	\N	\N	newmail2@mail.ru	$2a$10$Ec2VuJc3sAIkJmKxB7W5i.bCs1aH94nPNNslYjUV9GKe77h0gVpQS	user51	t
7	\N	\N	newmail7@mail.ru	$2a$10$wjWb0u1TWroJAVeRvqW2MuFkkKWfe/jVmhBQrryapP6OvB6hDCpGC	user61	t
8	\N	\N	newmail8@mail.ru	$2a$10$wMq9WEE1/GRhyEg88Nt1zOz9Yw/T8v8jmYN/mKwTylEGUy4ra2Seu	user71	t
9	\N	\N	newmail9@mail.ru	$2a$10$6Sxfcx7cX2sNLmHu4MYYO.lt.aRYna5bKqSg.eVS2jggab1TfB9k6	user8	t
10	\N	\N	newmail10@mail.ru	$2a$10$grL2OgzjzAAMyeSByTIVwu0bCUIwY.8ipnqksbsYpNmgKZ8CMKYaK	user9	t
11	new	mail11	newmail110@mail.ru	$2a$10$XYYQbGpe8LN1MEwFaB5x5.qcz4ZC212IVKaRGhj0iNy0yTs1NQDzG	user10	t
12	new	mail22	newmail220@mail.ru	$2a$10$b1uSMtpcVmaXbKRUwrim..aJzJuay/f2n.coFu0ImBkvsRRFpPG/C	user11	t
13	new	mail13	newmail13@mail.ru	$2a$10$AMMqfVbJXb7XzRqYHc8APO4z1YmEV66Kf.xUIuDEWqPNoQRo/QSVq	user12	t
17	new	mail13	newmail15@mail.ru	$2a$10$XBIS5I2E03cVAJQRqZXavuD3wffibiVGs1djoGKkfHXJwOea7KIB2	user16	t
\.


--
-- Data for Name: person_has_hobby; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_has_hobby (person_id, hobby_id) FROM stdin;
\.


--
-- Data for Name: person_has_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_has_role (person_id, role_id) FROM stdin;
2	2
1	1
3	1
4	1
5	1
6	1
7	1
8	1
9	1
10	1
11	1
12	1
13	1
17	1
\.


--
-- Data for Name: person_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_profile (person_id, sex, about_me, registration_date_time, person_pic, person_cover, number_bank_card, birthday, last_online) FROM stdin;
17	f	\N	2023-11-21	\N	\N	\N	\N	2023-11-21 16:19:11.848
\.


--
-- Data for Name: person_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_role (role_id, role_name) FROM stdin;
2	ROLE_ADMIN
1	ROLE_USER
\.


--
-- Data for Name: person_speak_language; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person_speak_language (person_id, language_id) FROM stdin;
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.post (post_id, person_id, description, placement_time) FROM stdin;
\.


--
-- Data for Name: remark; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.remark (remark_id, person_id, description, parent_id, post_id) FROM stdin;
\.


--
-- Name: dataobj_dataobj_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dataobj_dataobj_id_seq', 1, false);


--
-- Name: hobbytable_hobby_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hobbytable_hobby_id_seq', 1, false);


--
-- Name: languagetable_language_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.languagetable_language_id_seq', 1, false);


--
-- Name: messages_messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_messages_id_seq', 1, false);


--
-- Name: person_person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_person_id_seq', 17, true);


--
-- Name: personrole_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.personrole_role_id_seq', 1, false);


--
-- Name: post_post_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.post_post_id_seq', 1, false);


--
-- Name: remark_remark_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.remark_remark_id_seq', 1, false);


--
-- Name: data_obj dataobj_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.data_obj
    ADD CONSTRAINT dataobj_pkey PRIMARY KEY (data_obj_id);


--
-- Name: education education_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education
    ADD CONSTRAINT education_pkey PRIMARY KEY (person_id);


--
-- Name: favorites favorites_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_pkey PRIMARY KEY (person_id, post_id);


--
-- Name: friends friends_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friends
    ADD CONSTRAINT friends_pkey PRIMARY KEY (person_id, friend_id);


--
-- Name: has_data_obj has_dataobj_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_data_obj
    ADD CONSTRAINT has_dataobj_pkey PRIMARY KEY (entity, data_obj_id, entity_id);


--
-- Name: hobby_table hobbytable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hobby_table
    ADD CONSTRAINT hobbytable_pkey PRIMARY KEY (hobby_id);


--
-- Name: job job_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT job_pkey PRIMARY KEY (person_id);


--
-- Name: language_table languagetable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.language_table
    ADD CONSTRAINT languagetable_pkey PRIMARY KEY (language_id);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (messages_id);


--
-- Name: person_has_hobby person_has_hobby_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_has_hobby
    ADD CONSTRAINT person_has_hobby_pkey PRIMARY KEY (person_id, hobby_id);


--
-- Name: person_has_role person_has_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_has_role
    ADD CONSTRAINT person_has_role_pkey PRIMARY KEY (person_id, role_id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (person_id);


--
-- Name: person_profile person_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_profile
    ADD CONSTRAINT person_profile_pkey PRIMARY KEY (person_id);


--
-- Name: person_speak_language person_speak_language_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_speak_language
    ADD CONSTRAINT person_speak_language_pkey PRIMARY KEY (person_id, language_id);


--
-- Name: person_role personrole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_role
    ADD CONSTRAINT personrole_pkey PRIMARY KEY (role_id);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (post_id);


--
-- Name: remark remark_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.remark
    ADD CONSTRAINT remark_pkey PRIMARY KEY (remark_id);


--
-- Name: has_data_obj data_obj_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_data_obj
    ADD CONSTRAINT data_obj_id_fk FOREIGN KEY (data_obj_id) REFERENCES public.data_obj(data_obj_id) NOT VALID;


--
-- Name: has_data_obj entitymessage_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_data_obj
    ADD CONSTRAINT entitymessage_id_fk FOREIGN KEY (entity_id) REFERENCES public.messages(messages_id) NOT VALID;


--
-- Name: has_data_obj entitypost_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_data_obj
    ADD CONSTRAINT entitypost_id_fk FOREIGN KEY (entity_id) REFERENCES public.post(post_id) NOT VALID;


--
-- Name: has_data_obj entityremark_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.has_data_obj
    ADD CONSTRAINT entityremark_id_fk FOREIGN KEY (entity_id) REFERENCES public.remark(remark_id) NOT VALID;


--
-- Name: friends friend_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friends
    ADD CONSTRAINT friend_id_fk FOREIGN KEY (friend_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: messages fromperson_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT fromperson_id_fk FOREIGN KEY (from_person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: person_has_hobby hobby_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_has_hobby
    ADD CONSTRAINT hobby_id_fk FOREIGN KEY (hobby_id) REFERENCES public.hobby_table(hobby_id) NOT VALID;


--
-- Name: person_speak_language language_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_speak_language
    ADD CONSTRAINT language_id_fk FOREIGN KEY (language_id) REFERENCES public.language_table(language_id) NOT VALID;


--
-- Name: remark parent_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.remark
    ADD CONSTRAINT parent_id_fk FOREIGN KEY (parent_id) REFERENCES public.remark(remark_id) NOT VALID;


--
-- Name: education person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.education
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: favorites person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: friends person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friends
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: job person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.job
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: person_has_hobby person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_has_hobby
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: person_has_role person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_has_role
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: person_profile person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_profile
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: person_speak_language person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_speak_language
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: post person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: remark person_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.remark
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- Name: person_profile personcover_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_profile
    ADD CONSTRAINT personcover_fk FOREIGN KEY (person_cover) REFERENCES public.data_obj(data_obj_id) NOT VALID;


--
-- Name: person_profile personpic_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_profile
    ADD CONSTRAINT personpic_fk FOREIGN KEY (person_pic) REFERENCES public.data_obj(data_obj_id) NOT VALID;


--
-- Name: favorites post_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT post_id_fk FOREIGN KEY (post_id) REFERENCES public.post(post_id) NOT VALID;


--
-- Name: remark post_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.remark
    ADD CONSTRAINT post_id_fk FOREIGN KEY (post_id) REFERENCES public.post(post_id) NOT VALID;


--
-- Name: person_has_role role_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_has_role
    ADD CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES public.person_role(role_id) NOT VALID;


--
-- Name: messages toperson_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT toperson_id_fk FOREIGN KEY (to_person_id) REFERENCES public.person(person_id) NOT VALID;


--
-- PostgreSQL database dump complete
--

