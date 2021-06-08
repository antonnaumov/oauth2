package com.github.antonnaumov.oauth2;

/**
 * Authentication style enum.
 *
 * Different OAuth2 providers assuming to receive clientID & clientSecret in different forms.
 * <p>
 *     In summary:
 *     <ul>
 *          <li>Reddit only accepts client secret in the Authorization header</li>
 *          <li>Dropbox accepts either it in URL param or Auth header, but not both.</li>
 *          <li>Google only accepts URL param (not spec compliant?), not Auth header</li>
 *          <li>Stripe only accepts client secret in Auth header with Bearer method, not Basic</li>
 *      </ul>
 * </p>
 * <p>
 *     The enumeration value HEADER points the provider expecting the authentication secrets comes as the Auth header,
 *     and value PARAMS - as form parameters.
 * </p>
 * <p>
 *     The enumeration value AUTO points there are no preferences or specification known, and the correct way
 *     to transfer parameters will be identified automatically.
 * </p>
 */
public enum AuthStyle {
    AUTO, HEADER, PARAMS
}
