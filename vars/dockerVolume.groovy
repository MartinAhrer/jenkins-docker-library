/**
 * Created by martin on 28/06/2017.
 */

import at.softwarecraftsmen.docker.Volume

/**
 * A step (method) for creating a Docker volume
 * @param name volume name
 * @return volume id
 */
def createIf(def name) {
    return new Volume().createIf(name)
}
