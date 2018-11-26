create table "DOCUMENT" (
  id          BIGSERIAL      NOT NULL,
  timeprint  int NOT NULL,
  typedocument VARCHAR(10) NOT NULL,
  formatpage VARCHAR(10) NOT NULL
)

create table "PRINTINGDOCUMENT" (
  id          BIGSERIAL      NOT NULL,
  timeprint  int NOT NULL,
  typedocument VARCHAR(10) NOT NULL,
  formatpage VARCHAR(10) NOT NULL
)