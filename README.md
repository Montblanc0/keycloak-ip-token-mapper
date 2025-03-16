# IP Address Token Mapper for Keycloak

This is a Keycloak OIDC Protocol Mapper that adds the user's IP address to the token and/or to the access token response. The mapper retrieves the IP from the `UserSessionModel` and assigns it to a configurable token claim name.

<p align="center">
  <img src="https://i.ibb.co/9mnVJ32X/Screenshot-2025-03-16-225654.png" alt="IP Mapper configuration" width="380">
</p>

## Warning

This extension is built with Keycloak internal SPI v26.1.3. Compatibility with previous or future versions is not guaranteed.

## Installation

### Option 1 (recommended)

1. Download the latest jar from the [Releases page](https://github.com/Montblanc0/keycloak-ip-token-mapper/releases)
2. Copy it to `<IS_HOME>/providers`
3. Restart Keycloak

### Option 2

1. Clone the repository
   ```bash
   git clone https://github.com/montblanc0/keycloak-ip-token-mapper.git
   cd keycloak-ip-token-mapper.git
   ```

2. Sync with Maven to download dependencies
   ```bash
   mvn dependency:resolve
   ```

3. (Optional) Edit the `IPAddressMapper` class to your likings

4. Build the project
   ```bash
   mvn clean install
   ```

5. Copy the JAR file to your Keycloak providers directory
   ```bash
   cp target/ip-token-mapper-*.jar <IS_HOME>/providers/
   ```

6. Restart Keycloak

## Configuration

1. Navigate to **Client Scopes** in Keycloak Admin UI
2. Select the scope where you want to add the mapper
3. Go to the **Mappers** tab and click **Add Mapper ? By Configuration**
4. Select "IP Address"
5. Configure the mapper:
    - **Name**: A descriptive name for this mapper
    - **Token Claim Name**: Set the claim property name (can be a fully qualified name, e.g. `meta.connection.ip`)
    - Turn On/Off any place where you want the IP address to be mapped into

## License

Apache License 2.0