
import { NativeModules } from 'react-native'

const { RNContainsLocation } = NativeModules

export const containsLocation = (point, polygon) => {
  return new Promise((resolve, reject) => {
    RNContainsLocation.containsLocation(point, polygon, isWithinCoverage => {
      if (isWithinCoverage) {
        resolve(true)
      } else {
        resolve(false)
      }
    })
  })
}
