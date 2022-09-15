--
-- PostgreSQL database dump
--

-- Dumped from database version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Parts; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public."Parts" (
    id integer NOT NULL,
    descricao text,
    preco double precision,
    quantidade integer
);


ALTER TABLE public."Parts" OWNER TO ti2cc;

--
-- Name: Parts_id_seq; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

CREATE SEQUENCE public."Parts_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Parts_id_seq" OWNER TO ti2cc;

--
-- Name: Parts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ti2cc
--

ALTER SEQUENCE public."Parts_id_seq" OWNED BY public."Parts".id;


--
-- Name: Parts id; Type: DEFAULT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Parts" ALTER COLUMN id SET DEFAULT nextval('public."Parts_id_seq"'::regclass);


--
-- Name: Parts Parts_id_key; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public."Parts"
    ADD CONSTRAINT "Parts_id_key" UNIQUE (id);


--
-- PostgreSQL database dump complete
--

