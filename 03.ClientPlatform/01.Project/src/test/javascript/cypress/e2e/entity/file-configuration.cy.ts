import {
  entityTableSelector,
  entityDetailsButtonSelector,
  entityDetailsBackButtonSelector,
  entityCreateButtonSelector,
  entityCreateSaveButtonSelector,
  entityCreateCancelButtonSelector,
  entityEditButtonSelector,
  entityDeleteButtonSelector,
  entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('FileConfiguration e2e test', () => {
  const fileConfigurationPageUrl = '/highwaypj/file-configuration';
  const fileConfigurationPageUrlPattern = new RegExp('/highwaypj/file-configuration(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const fileConfigurationSample = { name: 'apropos flat joint', path: 'yum', types: 'for criteria' };

  let fileConfiguration;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/services/highwaypj/api/file-configurations+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/services/highwaypj/api/file-configurations').as('postEntityRequest');
    cy.intercept('DELETE', '/services/highwaypj/api/file-configurations/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (fileConfiguration) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/services/highwaypj/api/file-configurations/${fileConfiguration.id}`,
      }).then(() => {
        fileConfiguration = undefined;
      });
    }
  });

  it('FileConfigurations menu should load FileConfigurations page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('highwaypj/file-configuration');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('FileConfiguration').should('exist');
    cy.url().should('match', fileConfigurationPageUrlPattern);
  });

  describe('FileConfiguration page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(fileConfigurationPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create FileConfiguration page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/highwaypj/file-configuration/new$'));
        cy.getEntityCreateUpdateHeading('FileConfiguration');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', fileConfigurationPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/services/highwaypj/api/file-configurations',
          body: fileConfigurationSample,
        }).then(({ body }) => {
          fileConfiguration = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/services/highwaypj/api/file-configurations+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/services/highwaypj/api/file-configurations?page=0&size=20>; rel="last",<http://localhost/services/highwaypj/api/file-configurations?page=0&size=20>; rel="first"',
              },
              body: [fileConfiguration],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(fileConfigurationPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details FileConfiguration page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('fileConfiguration');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', fileConfigurationPageUrlPattern);
      });

      it('edit button click should load edit FileConfiguration page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('FileConfiguration');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', fileConfigurationPageUrlPattern);
      });

      it('edit button click should load edit FileConfiguration page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('FileConfiguration');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', fileConfigurationPageUrlPattern);
      });

      it('last delete button click should delete instance of FileConfiguration', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('fileConfiguration').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', fileConfigurationPageUrlPattern);

        fileConfiguration = undefined;
      });
    });
  });

  describe('new FileConfiguration page', () => {
    beforeEach(() => {
      cy.visit(`${fileConfigurationPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('FileConfiguration');
    });

    it('should create an instance of FileConfiguration', () => {
      cy.get(`[data-cy="name"]`).type('spanish');
      cy.get(`[data-cy="name"]`).should('have.value', 'spanish');

      cy.get(`[data-cy="description"]`).type('if fit edit');
      cy.get(`[data-cy="description"]`).should('have.value', 'if fit edit');

      cy.get(`[data-cy="path"]`).type('whispered setting compact');
      cy.get(`[data-cy="path"]`).should('have.value', 'whispered setting compact');

      cy.get(`[data-cy="types"]`).type('even hence impossible');
      cy.get(`[data-cy="types"]`).should('have.value', 'even hence impossible');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        fileConfiguration = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', fileConfigurationPageUrlPattern);
    });
  });
});
