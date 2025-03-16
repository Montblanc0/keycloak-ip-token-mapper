package it.montblanc0.keycloak.protocol.oidc.mappers;

import com.google.auto.service.AutoService;

import lombok.extern.jbosslog.JBossLog;
import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.ProtocolMapper;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenResponseMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.TokenIntrospectionTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.IDToken;

import java.util.ArrayList;
import java.util.List;


@JBossLog
@AutoService(ProtocolMapper.class)
public class IPAddressMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper,
		OIDCAccessTokenResponseMapper, TokenIntrospectionTokenMapper {

	private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();


	static {
		OIDCAttributeMapperHelper.addTokenClaimNameConfig(configProperties);
		OIDCAttributeMapperHelper.addIncludeInTokensConfig(configProperties, IPAddressMapper.class);
	}

	public static final String PROVIDER_ID = "oidc-ip-token-mapper";


	public List<ProviderConfigProperty> getConfigProperties() {
		return configProperties;
	}

	@Override
	public String getId() {
		return PROVIDER_ID;
	}

	@Override
	public String getDisplayType() {
		return "IP Address";
	}

	@Override
	public String getDisplayCategory() {
		return TOKEN_MAPPER_CATEGORY;
	}

	@Override
	public String getHelpText() {
		return "Adds the user IP address into the token.";
	}

	protected void setClaim(IDToken token, ProtocolMapperModel mappingModel, UserSessionModel userSession) {
		String attributeValue = userSession.getIpAddress();
		if (null == attributeValue || attributeValue.isBlank()) return;
		log.debugf("Mapping IP address: %s in token for user %s", attributeValue, userSession.getLoginUsername());
		OIDCAttributeMapperHelper.mapClaim(token, mappingModel, attributeValue);
	}

	@Override
	protected void setClaim(AccessTokenResponse accessTokenResponse, ProtocolMapperModel mappingModel, UserSessionModel userSession,
	                        KeycloakSession keycloakSession, ClientSessionContext clientSessionCtx) {

		String attributeValue = userSession.getIpAddress();
		if (null == attributeValue || attributeValue.isBlank()) return;
		log.debugf("Mapping IP address: %s in token for user %s", attributeValue, userSession.getLoginUsername());
		OIDCAttributeMapperHelper.mapClaim(accessTokenResponse, mappingModel, attributeValue);
	}

}