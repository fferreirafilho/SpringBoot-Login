CREATE TABLE TB_ROLES (
	ID UUID NOT NULL,
	DESCRIPTION VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	CONSTRAINT ROLES_PK PRIMARY KEY (ID),
	CONSTRAINT ROLES_CONSTRAINT_NAME_UNIQUE UNIQUE (NAME)
);

CREATE TABLE TB_ACCESS_GROUPS (
	ID UUID NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	CONSTRAINT ACCESS_GROUPS_PK PRIMARY KEY (ID),
	CONSTRAINT ACCESS_GROUPS_CONSTRAINT_NAME_UNIQUE UNIQUE (NAME)
);

CREATE TABLE TB_ROLES_GROUPS (
	ROLE_ID UUID NOT NULL,
	GROUP_ID UUID NOT NULL,
	CONSTRAINT ROLES_GROUPS_ROLES_FK FOREIGN KEY (ROLE_ID) REFERENCES TB_ROLES(ID),
	CONSTRAINT ROLES_GROUPS_ACCESS_GROUPS_FK FOREIGN KEY (GROUP_ID) REFERENCES TB_ACCESS_GROUPS(ID)
);