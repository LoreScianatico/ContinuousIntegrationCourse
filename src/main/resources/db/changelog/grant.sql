drop user recipebook if exists;
create user recipebook with encrypted password 'passwd';
grant select on all tables in schema public to recipebook;
grant insert on all tables in schema public to recipebook;
grant update on all tables in schema public to recipebook;
grant delete on all tables in schema public to recipebook;
grant select, usage on all sequences in schema public to recipebook;